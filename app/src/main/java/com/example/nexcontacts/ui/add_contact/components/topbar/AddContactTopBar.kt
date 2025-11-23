package com.example.nexcontacts.ui.add_contact.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.nexcontacts.ui.theme.AppTheme


@Composable
fun AddContactTopBar(
    onCancel: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Cancel",
            color = AppTheme.colors.primary,
            style = AppTheme.typography.bodyMedium,
            modifier = Modifier.clickable { onCancel() }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "New Contact",
            style = AppTheme.typography.titleXLarge,
            color = AppTheme.colors.textFifth
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "Done",
            color = AppTheme.colors.primary,
            style = AppTheme.typography.bodyLarge,
            modifier = Modifier.clickable { onDone() }
        )
    }
}

