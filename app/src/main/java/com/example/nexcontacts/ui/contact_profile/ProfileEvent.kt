package com.example.nexcontacts.ui.contact_profile

sealed class ProfileEvent {
    data object NavigateBack : ProfileEvent()
    data class ShowError(val message: String) : ProfileEvent()
}
