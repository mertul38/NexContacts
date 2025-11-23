package com.example.nexcontacts.ui.contact_profile

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexcontacts.data.di.ServiceLocator
import com.example.nexcontacts.ui.common.SnackbarController
import com.example.nexcontacts.utils.ImageUtils
import kotlinx.coroutines.launch

class ProfileScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ServiceLocator.userRepository

    var state = mutableStateOf(ProfileState())
        private set

    private val _event = mutableStateOf<ProfileEvent?>(null)
    val event get() = _event

    // --------------------------------------------------------
    // LOAD USER (REMOTE ONLY)
    // --------------------------------------------------------
    fun loadUser(id: String) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)

            val users = repo.getUsers()
            val user = users.find { it.id == id }

            state.value = state.value.copy(
                user = user,
                isLoading = false
            )
        }
    }

    // --------------------------------------------------------
    // UI State Updates (Primitive Only)
    // --------------------------------------------------------
    fun toggleEditMode() {
        state.value = state.value.copy(editMode = !state.value.editMode)
    }

    fun openEditMode() {
        if (!state.value.editMode)
            state.value = state.value.copy(editMode = true)
    }

    fun updatePhoto(newUri: String) {
        state.value = state.value.copy(newPhotoUri = newUri)
    }


    fun updateFirstName(value: String) {
        val u = state.value.user ?: return
        state.value = state.value.copy(user = u.copy(firstName = value))
    }

    fun updateLastName(value: String) {
        val u = state.value.user ?: return
        state.value = state.value.copy(user = u.copy(lastName = value))
    }

    fun updatePhone(value: String) {
        val u = state.value.user ?: return
        state.value = state.value.copy(user = u.copy(phoneNumber = value))
    }

    // --------------------------------------------------------
    // DELETE USER (REMOTE ONLY)
    // --------------------------------------------------------
    fun removeUser(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val user = state.value.user ?: return@launch
                repo.deleteUser(user)
                onSuccess()
                SnackbarController.show("User is deleted!")
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e.message ?: "Delete failed")
            }
        }
    }

    // --------------------------------------------------------
    // SAVE CHANGES (REMOTE ONLY)
    // --------------------------------------------------------
    fun saveChanges() {
        val user = state.value.user ?: return

        viewModelScope.launch {
            try {
                val uri = state.value.newPhotoUri

                val imageFile = uri?.let {
                    ImageUtils.uriToFile(getApplication(), it)
                }

                val updated = repo.updateUser(
                    user = user,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    phone = user.phoneNumber,
                    newImageFile = imageFile   // <-- ARTIK NULL DEĞİL
                )

                state.value = state.value.copy(
                    user = updated,
                    newPhotoUri = null,
                    editMode = false
                )

                _event.value = ProfileEvent.NavigateBack
                SnackbarController.show("User is updated!")

            }
            catch (e: Exception) {
                e.printStackTrace()
                _event.value = ProfileEvent.ShowError(e.message ?: "Update failed")
            }
        }
    }


    fun consumeEvent() {
        _event.value = null
    }
}
