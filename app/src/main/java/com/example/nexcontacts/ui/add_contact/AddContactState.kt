package com.example.nexcontacts.ui.add_contact

data class AddContactState(
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val photoUri: String? = null,
    val isLoading: Boolean = false
)
