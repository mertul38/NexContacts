package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.data.remote.dto.UserDto

@Composable
fun ContactGroupSection(
    letter: Char,
    users: List<UserDto>,
    onContactClicked: (UserDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp) // space *between* group sections
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
                )  // inner padding inside rounded box
    ) {
        LetterHeader(letter)

        users.forEach { user ->
            ContactItem(
                user = user,
                onClick = { onContactClicked(user) }
            )
        }
    }
}
