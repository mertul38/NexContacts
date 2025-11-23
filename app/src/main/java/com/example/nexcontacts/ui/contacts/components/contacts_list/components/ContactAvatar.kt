package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nexcontacts.ui.theme.AppTheme


@Composable
fun ContactAvatar(
    imageUrl: String?,
    firstName: String
) {
    val fallbackLetter = firstName.firstOrNull()?.uppercaseChar()?.toString() ?: "?"

    if (imageUrl.isNullOrBlank()) {
        // --- HARF AVATAR ---
        Box(
            modifier = Modifier
                .size(48.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.06f),
                    spotColor = Color.Black.copy(alpha = 0.06f)
                )
                .clip(CircleShape)
                .background(AppTheme.colors.primaryLight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fallbackLetter,
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colors.primary
            )
        }

    } else {
        // --- NORMAL FOTOĞRAF ---
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
    }
}
