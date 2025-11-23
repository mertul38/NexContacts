package com.example.nexcontacts.ui.contacts

import ContactsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexcontacts.data.di.ServiceLocator
import com.example.nexcontacts.domain.model.User
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {

    private val repo = ServiceLocator.userRepository

    var state by mutableStateOf(ContactsState())
        private set

    var event: ContactsEvent? by mutableStateOf(null)
        private set

    init {
        loadRemoteUsers()
    }

    private fun loadRemoteUsers() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val users = repo.getUsers()

            state = state.copy(
                users = users,
                isLoading = false
            )

            applySearchAndGrouping(state.search, users)
        }
    }

    fun onSearchChanged(query: String) {
        state = state.copy(search = query)
        applySearchAndGrouping(query, state.users)
    }

    private fun applySearchAndGrouping(query: String, users: List<User>) {

        val filtered = if (query.isBlank()) users else {
            users.filter {
                "${it.firstName} ${it.lastName}".lowercase().contains(query.lowercase())
            }
        }

        val grouped = if (query.isBlank()) {
            filtered
                .sortedBy { it.firstName.lowercase() }
                .groupBy { it.firstName.first().uppercaseChar() }
        } else {
            emptyMap()
        }

        state = state.copy(
            groupedUsers = grouped,
            searchResults = filtered
        )
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repo.deleteUser(user)
            val updated = state.users.filter { it.id != user.id }
            state = state.copy(users = updated)
            applySearchAndGrouping(state.search, updated)
        }
    }

    fun onContactClicked(user: User) {
        event = ContactsEvent.NavigateToProfile(user.id)
    }

    fun onEditContact(user: User) {
        event = ContactsEvent.NavigateToProfile(user.id, editModeOpen = true)
    }

    fun consumeEvent() { event = null }
    fun refresh() { loadRemoteUsers() }
}
