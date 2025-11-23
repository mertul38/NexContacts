package com.example.nexcontacts.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private const val BASE_URL = "http://146.59.52.68:11235/"
    private const val API_KEY = "b00514e5-f1a1-45d9-9258-cc58a4234af8"

    private val keyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("ApiKey", API_KEY)
            .build()
        chain.proceed(request)
    }

    private val logging = HttpLoggingInterceptor { message ->
        Log.d("HTTP", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(keyInterceptor)
        .addInterceptor(logging)
        .build()


    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
