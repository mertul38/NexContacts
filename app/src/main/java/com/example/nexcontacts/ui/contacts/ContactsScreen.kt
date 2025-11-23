package com.example.nexcontacts.ui.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexcontacts.ui.contacts.components.*
import com.example.nexcontacts.ui.contacts.components.contacts_list.ContactsList
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.SearchBar
import com.example.nexcontacts.ui.contacts.components.topbar.ContactsTopBar

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = viewModel(),
    onAddClicked: () -> Unit = {},
    onProfileClicked: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    val state = viewModel.state
    val grouped = state.groupedUsers
    val event = viewModel.event


    LaunchedEffect(event) {
        when (event) {
            is ContactsEvent.NavigateToProfile -> {
                onProfileClicked(event.userId)
                viewModel.consumeEvent()
            }
            null -> Unit
        }
    }

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        ContactsTopBar(onAddClicked)
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            value = state.search,
            onValueChange = viewModel::onSearchChanged
        )

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))

        if (grouped.isEmpty()) {
            EmptyContactsView(onAddClicked)
        } else {
            ContactsList(
                grouped,
                onContactClicked = viewModel::onContactClicked
            )
        }
    }
}

