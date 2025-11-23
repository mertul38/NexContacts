package com.example.nexcontacts.domain.mapper

import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.data.remote.dto.UserDto

fun UserDto.toDomain(localPath: String? = null): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        remoteImageUrl = profileImageUrl,
        localImagePath = localPath,
        createdAt = createdAt
    )
}

