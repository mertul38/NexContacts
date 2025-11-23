package com.example.nexcontacts.data.remote.response

data class DeleteUserResponse(
    val success: Boolean,
    val messages: List<String>,
    val data: Any?,
    val status: Int
)
