package com.example.nexcontacts.ui.contact_profile

import com.example.nexcontacts.domain.model.User

data class ProfileState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val user: User? = null,
    val editMode: Boolean = false,
    val newPhotoUri: String? = null  // sadece local seçilmiş foto

)
