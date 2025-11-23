package com.example.nexcontacts.ui.contact_profile

import com.example.nexcontacts.data.remote.dto.UserDto

data class ProfileState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val user: UserDto? = null,
    val editMode: Boolean = false
)
