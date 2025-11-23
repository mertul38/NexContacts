package com.example.nexcontacts.ui.contacts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.LocalAppColors
import com.example.nexcontacts.ui.theme.LocalAppTypography

@Composable
fun EmptyContactsView(
    onAddClicked: () -> Unit = {}
) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Avatar icon
        Icon(
            painter = painterResource(id = R.drawable.empty_avatar),
            contentDescription = null,
            tint = colors.onSurfaceLight,
            modifier = Modifier.size(72.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "No Contacts",
            style = typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Subtitle
        Text(
            text = "Contacts you’ve added will appear here.",
            style = typography.bodyMedium,
            color = colors.textSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button-like link text
        Text(
            text = "Create New Contact",
            style = typography.bodyLarge,
            color = colors.primary,
            modifier = Modifier.clickable { onAddClicked() }
        )
    }
}
