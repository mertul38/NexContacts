package com.example.nexcontacts.ui.contact_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexcontacts.data.remote.ApiResult
import com.example.nexcontacts.data.remote.UserRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

class ProfileScreenViewModel : ViewModel() {

    private val repo = UserRepository()

    var state = mutableStateOf(ProfileState())
        private set
    private val _event = mutableStateOf<ProfileEvent?>(null)
    val event: State<ProfileEvent?> = _event

    fun loadUser(id: String) {
        state.value = state.value.copy(isLoading = true)

        viewModelScope.launch {
            when (val result = repo.getUserById(id)) {
                is ApiResult.Success -> {
                    state.value = state.value.copy(
                        user = result.data,
                        isLoading = false
                    )
                }
                is ApiResult.Error -> {
                    state.value = state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun removeUser(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            when (val result = repo.deleteUser(id)) {
                is ApiResult.Success -> {
                    onSuccess()
                }
                is ApiResult.Error -> {
                    onError(result.message)
                }
            }
        }
    }


    fun toggleEditMode() {
        state.value = state.value.copy(editMode = !state.value.editMode)
    }

    fun updatePhoto(newUri: String) {
        val u = state.value.user ?: return
        state.value = state.value.copy(
            user = u.copy(profileImageUrl = newUri)
        )
    }

    fun updateFirstName(value: String) {
        state.value = state.value.copy(
            user = state.value.user?.copy(firstName = value)
        )
    }

    fun updateLastName(value: String) {
        state.value = state.value.copy(
            user = state.value.user?.copy(lastName = value)
        )
    }

    fun updatePhone(value: String) {
        state.value = state.value.copy(
            user = state.value.user?.copy(phoneNumber = value)
        )
    }

    fun saveChanges() {
        val user = state.value.user ?: return

        viewModelScope.launch {
            when (val result = repo.updateUser(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                phone = user.phoneNumber,
                imageUrl = user.profileImageUrl
            )) {
                is ApiResult.Success -> {
                    state.value = state.value.copy(editMode = false)
                    _event.value = ProfileEvent.NavigateBack   // <<--- navigation sinyali
                }
                is ApiResult.Error -> {
                    _event.value = ProfileEvent.ShowError(result.message)
                }
            }
        }
    }

    fun consumeEvent() {
        _event.value = null
    }


}
