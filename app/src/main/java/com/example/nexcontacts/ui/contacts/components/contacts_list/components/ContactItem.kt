package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.data.remote.dto.UserDto
import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun ContactItem(
    user: User,
    onClick: (User) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick(user) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactAvatar(
            localPath = user.localImagePath,
            remoteUrl = user.remoteImageUrl,
            firstName = user.firstName
        )


        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colors.textSecondary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = user.phoneNumber,
                style = AppTheme.typography.labelSmall,
                color = AppTheme.colors.textForth
            )
        }
    }
}
