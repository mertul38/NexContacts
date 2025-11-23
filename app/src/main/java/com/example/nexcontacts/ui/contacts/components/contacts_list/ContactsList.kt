package com.example.nexcontacts.ui.contacts.components.contacts_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.contacts.components.contacts_list.components.ContactGroupSection

@Composable
fun ContactsList(
    groupedUsers: Map<Char, List<User>>,
    onContactClicked: (User) -> Unit
) {
    LazyColumn {

        groupedUsers.toSortedMap().forEach { (letter, users) ->

            // Her harf için TEK bölüm
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
