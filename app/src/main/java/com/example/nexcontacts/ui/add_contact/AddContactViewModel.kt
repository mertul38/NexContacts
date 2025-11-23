package com.example.nexcontacts.ui.add_contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexcontacts.data.remote.ApiResult
import com.example.nexcontacts.data.remote.UserRepository
import kotlinx.coroutines.launch

class AddContactViewModel : ViewModel() {
    private val repository = UserRepository()
    var state by mutableStateOf(AddContactState())
        private set

    fun onFirstNameChanged(value: String) {
        state = state.copy(firstName = value)
    }

    fun onLastNameChanged(value: String) {
        state = state.copy(lastName = value)
    }

    fun onPhoneChanged(value: String) {
        state = state.copy(phone = value)
    }
    fun onPhotoSelected(uri: String) {
        state = state.copy(photoUri = uri)
    }


    fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun onDone(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (state.firstName.isBlank()) {
            onError("First name is required.")
            return
        }

        if (state.phone.isBlank()) {
            onError("Phone number is required.")
            return
        }

        // TODO: Phone validation tekrar açılacak
//    if (!PhoneUtils.isValidPhone(state.phone)) {
//        onError("Phone number is invalid.")
//        return
//    }

        // Backend’e kaydet
        saveToBackend(onSuccess, onError)
    }

    fun saveToBackend(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            when (val result = repository.createUser(
                state.firstName,
                state.lastName,
                state.phone,
                state.photoUri
            )) {
                is ApiResult.Success -> onSuccess()
                is ApiResult.Error -> onError(result.message)
            }
        }
    }

}
