package com.example.nexcontacts.data.remote

import com.example.nexcontacts.data.remote.dto.*
import com.example.nexcontacts.data.remote.request.CreateUserRequest
import com.example.nexcontacts.data.remote.request.UpdateUserRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserRepository {

    suspend fun createUser(
        firstName: String,
        lastName: String,
        phone: String,
        imageUrl: String?
    ): ApiResult<Unit> =
        safeCall {
            val body = CreateUserRequest(
                firstName, lastName, phone, imageUrl
            )
            val res = NetworkClient.api.createUser(body)
            if (res.success) ApiResult.Success(Unit)
            else ApiResult.Error(res.messages.firstOrNull() ?: "Unknown error")
        }

    suspend fun getAllUsers(): ApiResult<List<UserDto>> =
        safeCall {
            val res = NetworkClient.api.getAllUsers()
            if (res.success && res.data != null)
                ApiResult.Success(res.data.users)
            else ApiResult.Error(res.messages.firstOrNull() ?: "Unknown error")
        }

    suspend fun getUserById(id: String): ApiResult<UserDto> =
        safeCall {
            val res = NetworkClient.api.getUserById(id)
            if (res.success && res.data != null)
                ApiResult.Success(res.data)
            else ApiResult.Error("User not found")
        }

    suspend fun updateUser(
        id: String,
        firstName: String,
        lastName: String,
        phone: String,
        imageUrl: String?
    ): ApiResult<UserDto> =
        safeCall {
            val body = UpdateUserRequest(firstName, lastName, phone, imageUrl)
            val res = NetworkClient.api.updateUser(id, body)

            if (res.success && res.data != null)
                ApiResult.Success(res.data)
            else ApiResult.Error("Update failed")
        }

    suspend fun deleteUser(id: String): ApiResult<Unit> =
        safeCall {
            val res = NetworkClient.api.deleteUser(id)
            if (res.success) ApiResult.Success(Unit)
            else ApiResult.Error("Delete failed")
        }

    suspend fun uploadImage(file: File): ApiResult<String> =
        safeCall {
            val reqFile = file
                .asRequestBody("image/*".toMediaTypeOrNull())

            val body = MultipartBody.Part.createFormData(
                "image", file.name, reqFile
            )

            val res = NetworkClient.api.uploadImage(body)

            if (res.success && res.data != null)
                ApiResult.Success(res.data.imageUrl)
            else ApiResult.Error("Upload failed")
        }

    private suspend fun <T> safeCall(block: suspend () -> ApiResult<T>): ApiResult<T> =
        try { block() } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
}
