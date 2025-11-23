package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwipeBackground(
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .width(72.dp)
                .height(48.dp)
                .background(Color.Red, RoundedCornerShape(12.dp))
                .clickable { onDelete() },
            contentAlignment = Alignment.Center
        ) {
            Text("Delete", color = Color.White)
        }

        Box(
            modifier = Modifier
                .width(72.dp)
                .height(48.dp)
                .background(Color(0xFF4CAF50), RoundedCornerShape(12.dp))
                .clickable { onEdit() },
            contentAlignment = Alignment.Center
        ) {
            Text("Edit", color = Color.White)
        }
    }
}
