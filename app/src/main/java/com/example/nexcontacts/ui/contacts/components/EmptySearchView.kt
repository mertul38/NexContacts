package com.example.nexcontacts.ui.contacts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.LocalAppColors
import com.example.nexcontacts.ui.theme.LocalAppTypography

@Composable
fun EmptySearchView(
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


        Box(
            modifier = Modifier
                .size(96.dp)
                .background(
                    color = colors.onSurfaceLight,   // Outer circle color
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search_not_found),
                contentDescription = null,
                tint = Color.White,                  // Icon is WHITE
                modifier = Modifier.size(56.dp)      // Icon size 56×56
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "No Results",
            style = typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Subtitle
        Text(
            text = "The user you are looking forcould not be\nfound.",
            textAlign = TextAlign.Center,
            style = typography.bodyMedium,
            color = colors.textSecondary
        )


    }
}
