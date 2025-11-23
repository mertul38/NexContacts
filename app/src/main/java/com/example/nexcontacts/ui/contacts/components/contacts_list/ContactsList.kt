package com.example.nexcontacts.ui.contacts.components.contacts_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.ContactGroupSection
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.LetterHeader
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.SwipeableContactItem

@Composable
fun ContactsList(
    groupedUsers: Map<Char, List<User>>,
    onContactClicked: (User) -> Unit,
    onEditContact: (User) -> Unit,
    onDeleteContact: (User) -> Unit
) {
    LazyColumn {
        groupedUsers.toSortedMap().forEach { (letter, users) ->
            item {
                ContactGroupSection(
                    letter = letter,
                    users = users,
                    onContactClicked = onContactClicked,
                    onEditContact = onEditContact,
                    onDeleteContact = onDeleteContact
                )
            }
        }
    }
}

