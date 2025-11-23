package com.example.nexcontacts.ui.navigation

object Routes {
    const val CONTACTS = "contacts"
    const val ADD_CONTACT = "add_contact"
    const val SUCCESS = "success"
    const val PROFILE = "profile/{userId}"
    fun profile(userId: String) = "profile/$userId"

}
