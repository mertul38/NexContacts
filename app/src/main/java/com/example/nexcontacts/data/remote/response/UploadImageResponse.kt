package com.example.nexcontacts.data.remote.response

data class UploadImageResponse(
    val success: Boolean,
    val messages: List<String>,
    val data: UploadImageData?,
    val status: Int
)

data class UploadImageData(
    val imageUrl: String
)
