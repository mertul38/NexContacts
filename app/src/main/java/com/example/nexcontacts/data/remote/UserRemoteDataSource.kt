package com.example.nexcontacts.data.remote

import com.example.nexcontacts.data.remote.dto.UserDto
import com.example.nexcontacts.data.remote.request.CreateUserRequest
import com.example.nexcontacts.data.remote.request.UpdateUserRequest
import com.example.nexcontacts.data.remote.response.UploadImageResponse
import java.io.File
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class UserRemoteDataSource(
    private val api: ApiService
) {

    suspend fun fetchAll() = api.getAllUsers()
    suspend fun get(id: String) = api.getUserById(id)
    suspend fun create(body: CreateUserRequest) = api.createUser(body)
    suspend fun update(id: String, body: UpdateUserRequest) = api.updateUser(id, body)
    suspend fun delete(id: String) = api.deleteUser(id)

    // ✅ Yeni eklendi — dosya upload işlemi
    suspend fun uploadImage(file: File): UploadImageResponse? {
        val body = MultipartBody.Part.createFormData(
            name = "image",
            filename = file.name,
            body = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        )
        return api.uploadImage(body)
    }
}
