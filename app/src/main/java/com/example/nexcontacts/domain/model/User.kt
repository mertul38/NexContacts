package com.example.nexcontacts.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class User(
    val id: String,
    val createdAt: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val remoteImageUrl: String?,     // URL from backend
    val localImagePath: String?      // File saved on device
)

