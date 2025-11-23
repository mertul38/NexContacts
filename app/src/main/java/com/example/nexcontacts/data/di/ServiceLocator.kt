package com.example.nexcontacts.data.di

import android.content.Context
import com.example.nexcontacts.data.remote.NetworkClient
import com.example.nexcontacts.data.remote.UserRemoteDataSource
import com.example.nexcontacts.data.repository.UserRepository

object ServiceLocator {

    lateinit var userRepository: UserRepository
        private set

    fun init(context: Context) {
        val remote = UserRemoteDataSource(NetworkClient.api)

        userRepository = UserRepository(
            remote = remote
        )
    }
}
