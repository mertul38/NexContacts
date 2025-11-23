package com.example.nexcontacts.data.remote

import GetAllUsersResponse
import com.example.nexcontacts.data.remote.dto.*
import com.example.nexcontacts.data.remote.request.CreateUserRequest
import com.example.nexcontacts.data.remote.request.UpdateUserRequest
import com.example.nexcontacts.data.remote.response.CreateUserResponse
import com.example.nexcontacts.data.remote.response.DeleteUserResponse
import com.example.nexcontacts.data.remote.response.GetUserResponse
import com.example.nexcontacts.data.remote.response.UpdateUserResponse
import com.example.nexcontacts.data.remote.response.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @POST("api/User")
    suspend fun createUser(
        @Body body: CreateUserRequest
    ): CreateUserResponse

    @GET("api/User/GetAll")
    suspend fun getAllUsers(): GetAllUsersResponse

    @GET("api/User/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): GetUserResponse

    @PUT("api/User/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body body: UpdateUserRequest
    ): UpdateUserResponse

    @DELETE("api/User/{id}")
    suspend fun deleteUser(
        @Path("id") id: String
    ): DeleteUserResponse

    @Multipart
    @POST("api/User/UploadImage")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): UploadImageResponse
}
