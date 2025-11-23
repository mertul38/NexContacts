package com.example.nexcontacts.data.remote.response

import com.example.nexcontacts.data.remote.dto.UserDto

data class CreateUserResponse(
    val success: Boolean,
    val messages: List<String>,
    val data: UserDto?,
    val status: Int
)