package com.example.nexcontacts.ui.contacts

import ContactsState
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexcontacts.data.remote.ApiResult
import com.example.nexcontacts.data.remote.UserRepository
import com.example.nexcontacts.data.remote.dto.UserDto
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {

    private val repo = UserRepository()
    var state by mutableStateOf(ContactsState())
        private set
    var event: ContactsEvent? by mutableStateOf(null)
        private set

    fun loadUsers() {
        viewModelScope.launch {
            when (val result = repo.getAllUsers()) {

                is ApiResult.Success -> {
                    val list = result.data
                    val grouped = list
                        .sortedBy { it.firstName.lowercase() }
                        .groupBy { it.firstName.first().uppercaseChar() }

                    state = state.copy(
                        users = list,
                        groupedUsers = grouped
                    )
                }

                is ApiResult.Error -> {
                    state = state.copy(error = result.message)
                }
            }
        }
    }

    fun onSearchChanged(query: String) {
        state = state.copy(search = query)

        val filtered = if (query.isBlank()) {
            state.users
        } else {
            state.users.filter {
                "${it.firstName} ${it.lastName}".lowercase().contains(query.lowercase())
            }
        }

        val grouped = filtered
            .sortedBy { it.firstName.lowercase() }
            .groupBy { it.firstName.first().uppercaseChar() }

        state = state.copy(groupedUsers = grouped)
    }

    fun onContactClicked(userDto: UserDto) {
        event = ContactsEvent.NavigateToProfile(userDto.id)
    }

    fun consumeEvent() {
        event = null
    }
}

