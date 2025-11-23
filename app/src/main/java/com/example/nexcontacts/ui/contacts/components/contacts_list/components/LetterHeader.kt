package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun LetterHeader(letter: Char) {
    Text(
        text = letter.toString(),
        color = AppTheme.colors.textTertiary,
        style = AppTheme.typography.labelMedium,
        modifier = Modifier
            .fillMaxWidth()
    )
}
