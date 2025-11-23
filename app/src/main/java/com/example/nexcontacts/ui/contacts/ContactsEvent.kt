package com.example.nexcontacts.ui.contacts

sealed class ContactsEvent {
    data class NavigateToProfile(val userId: String) : ContactsEvent()
}
