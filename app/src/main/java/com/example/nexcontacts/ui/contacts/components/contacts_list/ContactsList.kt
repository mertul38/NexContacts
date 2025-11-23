package com.example.nexcontacts.ui.contacts.components.contacts_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.nexcontacts.data.remote.dto.UserDto
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.ContactGroupSection

@Composable
fun ContactsList(
    groupedUsers: Map<Char, List<UserDto>>,
    onContactClicked: (UserDto) -> Unit
) {
    LazyColumn {
        groupedUsers.forEach { (letter, users) ->
            item {
                ContactGroupSection(
                    letter = letter,
                    users = users,
                    onContactClicked = onContactClicked
                )
            }
        }
    }
}
