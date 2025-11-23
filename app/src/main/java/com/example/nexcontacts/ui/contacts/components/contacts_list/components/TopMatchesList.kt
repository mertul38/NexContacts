package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun TopMatchesList(
    users: List<User>,
    onContactClicked: (User) -> Unit,
    onEditContact: (User) -> Unit,
    onDeleteContact: (User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Text(
            "TOP NAME MATCHES",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.textSecondary,
            modifier = Modifier.padding(8.dp)
        )

        users.forEach { user ->
            SwipeableContactItem(
                user = user,
                onEdit = onEditContact,
                onDelete = onDeleteContact,
                onClick = onContactClicked
            )
        }
    }
}
