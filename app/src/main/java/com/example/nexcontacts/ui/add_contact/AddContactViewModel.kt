package com.example.nexcontacts.ui.add_contact

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.nexcontacts.data.di.ServiceLocator
import com.example.nexcontacts.utils.ImageUtils
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel() {

    private val repository = ServiceLocator.userRepository

    var state by mutableStateOf(AddContactState())
        private set

    fun onFirstNameChanged(value: String) { state = state.copy(firstName = value) }
    fun onLastNameChanged(value: String) { state = state.copy(lastName = value) }
    fun onPhoneChanged(value: String) { state = state.copy(phone = value) }
    fun onPhotoSelected(uri: String) { state = state.copy(photoUri = uri) }

    fun onDone(context: Context, onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (state.firstName.isBlank()) return onError("First name is required.")
        if (state.phone.isBlank()) return onError("Phone number is required.")
        saveToBackend(context, onSuccess, onError)
    }

    private fun saveToBackend(context: Context, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val tempFile = state.photoUri?.let { uri ->
                    ImageUtils.uriToFile(context, uri)
                }

                val newUser = repository.createUser(
                    firstName = state.firstName,
                    lastName = state.lastName,
                    phone = state.phone,
                    imageFile = tempFile
                )

                if (newUser == null) {
                    onError("Failed to create user")
                    return@launch
                }

                // ⬅ Contacts listesi geri dönünce refresh edecek
                onSuccess()

            } catch (e: Exception) {
                e.printStackTrace()
                onError(e.message ?: "Unexpected error")
            }
        }
    }
}
