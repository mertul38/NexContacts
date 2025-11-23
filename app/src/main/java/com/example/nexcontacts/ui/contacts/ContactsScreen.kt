package com.example.nexcontacts.ui.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.contact_profile.components.topbar.DeleteModalSheet
import com.example.nexcontacts.ui.contacts.components.*
import com.example.nexcontacts.ui.contacts.components.contacts_list.ContactsList
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.SearchBar
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.SearchHistorySection
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.TopMatchesList
import com.example.nexcontacts.ui.contacts.components.topbar.ContactsTopBar

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = viewModel(),
    onAddClicked: () -> Unit = {},
    onProfileClicked: (String, Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var showDeleteSheet by remember { mutableStateOf(false) }
    var userToDelete by remember { mutableStateOf<User?>(null) }

    var isSearchFocused by remember { mutableStateOf(false) }
    var showHistory by remember { mutableStateOf(false) }

    val mockHistory = listOf(
        "mert",
        "ali",
        "john doe",
        "ayşe kaya"
    )

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    val state = viewModel.state
    val grouped = state.groupedUsers
    val event = viewModel.event

    LaunchedEffect(event) {
        when (event) {
            is ContactsEvent.NavigateToProfile -> {
                onProfileClicked(event.userId, event.editModeOpen)
                viewModel.consumeEvent()
            }
            null -> Unit
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
                showHistory = false
            }
    ) {

        Spacer(Modifier.height(24.dp))
        ContactsTopBar(onAddClicked)
        Spacer(Modifier.height(16.dp))

        SearchBar(
            value = state.search,
            onValueChange = { newValue ->
                viewModel.onSearchChanged(newValue)


                if (newValue.isNotBlank()) {
                    showHistory = false
                }

                else if (newValue.isBlank() && isSearchFocused) {
                    showHistory = true
                }
            },
            onFocusChanged = { focused ->
                isSearchFocused = focused
                showHistory = focused && state.search.isBlank()
            }
        )


        Spacer(Modifier.height(16.dp))


        if (showHistory) {
            SearchHistorySection(
                history = mockHistory,
                onSelect = { selected ->
                    viewModel.onSearchChanged(selected)
                    showHistory = false
                },
                onRemove = { /* TODO */ },
                onClearAll = { /* TODO */ }
            )
            return@Column
        }


        if (state.search.isNotBlank()) {

            if (state.searchResults.isEmpty()) {
                EmptySearchView(onAddClicked)
            } else {
                TopMatchesList(
                    users = state.searchResults,
                    onContactClicked = viewModel::onContactClicked,
                    onEditContact = viewModel::onEditContact,
                    onDeleteContact = { user ->
                        userToDelete = user
                        showDeleteSheet = true
                    }
                )

            }
            return@Column // 🔥 normal listeyi göstermeyi kes!
        }


        if (grouped.isEmpty()) {
            EmptyContactsView(onAddClicked)
        } else {
            ContactsList(
                groupedUsers = grouped,
                onContactClicked = viewModel::onContactClicked,
                onEditContact = viewModel::onEditContact,
                onDeleteContact = { user ->
                    userToDelete = user
                    showDeleteSheet = true
                }
            )

        }
    }

    if (showDeleteSheet && userToDelete != null) {
        DeleteModalSheet(
            userName = "${userToDelete!!.firstName}",
            onDeleteClicked = {
                showDeleteSheet = false


                viewModel.deleteUser(userToDelete!!)
            },
            onCancelClicked = {
                showDeleteSheet = false
            },
            onDismissRequest = {
                showDeleteSheet = false
            }
        )
    }

}


