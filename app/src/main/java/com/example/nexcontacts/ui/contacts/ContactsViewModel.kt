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

    // --------------------------------------------------------
    // FETCH REMOTE USERS
    // --------------------------------------------------------
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

    // --------------------------------------------------------
    // SEARCH
    // --------------------------------------------------------
    fun onSearchChanged(query: String) {
        state = state.copy(search = query)
        applySearchAndGrouping(query, state.users)
    }

    private fun applySearchAndGrouping(query: String, users: List<User>) {
        val filtered = if (query.isBlank()) {
            users
        } else {
            users.filter {
                "${it.firstName} ${it.lastName}"
                    .lowercase()
                    .contains(query.lowercase())
            }
        }

        val grouped = filtered
            .sortedBy { it.firstName.lowercase() }
            .groupBy { it.firstName.first().uppercaseChar() }

        state = state.copy(
            groupedUsers = grouped
        )
    }

    // --------------------------------------------------------
    // NAVIGATION
    // --------------------------------------------------------
    fun onContactClicked(user: User) {
        event = ContactsEvent.NavigateToProfile(user.id)
    }

    fun consumeEvent() {
        event = null
    }

    fun refresh() {
        loadRemoteUsers()
    }

}
