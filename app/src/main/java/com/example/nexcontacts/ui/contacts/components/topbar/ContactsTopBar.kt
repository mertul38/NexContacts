package com.example.nexcontacts.ui.contacts.components.topbar

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
import com.example.nexcontacts.ui.theme.AppTheme
import com.example.nexcontacts.ui.theme.LocalAppColors
import com.example.nexcontacts.ui.theme.LocalAppTypography

@Composable
fun ContactsTopBar(
    onAddClicked: () -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Title
        Text(
            text = "Contacts",
            style = AppTheme.typography.headlineLarge,
            color = AppTheme.colors.onSurfaceDark
        )

        Spacer(modifier = Modifier.weight(1f))

        // Plus Circle Icon Button
        Icon(
            painter = painterResource(id = R.drawable.plus_circle),
            contentDescription = "Add Contact",
            modifier = Modifier
                .size(24.dp)
                .clickable { onAddClicked() },
            tint = AppTheme.colors.primary
        )
    }
}
