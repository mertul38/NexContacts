package com.example.nexcontacts.data.repository

import com.example.nexcontacts.data.remote.UserRemoteDataSource
import com.example.nexcontacts.data.remote.request.CreateUserRequest
import com.example.nexcontacts.data.remote.request.UpdateUserRequest
import com.example.nexcontacts.domain.mapper.toDomain
import com.example.nexcontacts.domain.model.User
import java.io.File

class UserRepository(
    private val remote: UserRemoteDataSource
) {

    suspend fun getUsers(): List<User> {
        val res = remote.fetchAll()
        if (!res.success || res.data == null) return emptyList()

        return res.data.users.map { dto ->
            dto.toDomain(localPath = null)
        }
    }

    suspend fun createUser(
        firstName: String,
        lastName: String,
        phone: String,
        imageFile: File?
    ): User? {

        val createReq = CreateUserRequest(firstName, lastName, phone, null)
        val createRes = remote.create(createReq)
        if (!createRes.success || createRes.data == null) return null

        val userDto = createRes.data
        var uploadedUrl: String? = null

        if (imageFile != null) {
            val uploadRes = remote.uploadImage(imageFile)
            uploadedUrl = uploadRes?.data?.imageUrl

            if (!uploadedUrl.isNullOrEmpty()) {
                val updateReq = UpdateUserRequest(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phone,
                    profileImageUrl = uploadedUrl
                )
                remote.update(userDto.id, updateReq)
            }
        }

        return userDto.copy(
            profileImageUrl = uploadedUrl ?: userDto.profileImageUrl
        ).toDomain(localPath = null)
    }


    suspend fun updateUser(
        user: User,
        firstName: String,
        lastName: String,
        phone: String,
        newImageFile: File?
    ): User? {

        var finalImageUrl = user.remoteImageUrl

        if (newImageFile != null) {
            val uploadRes = remote.uploadImage(newImageFile)
            finalImageUrl = uploadRes?.data?.imageUrl ?: user.remoteImageUrl
        }

        val req = UpdateUserRequest(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phone,
            profileImageUrl = finalImageUrl
        )
        val res = remote.update(user.id, req)

        if (!res.success || res.data == null) return null

        return res.data.toDomain(localPath = null)
    }



    suspend fun deleteUser(user: User) {
        remote.delete(user.id)
    }
}
