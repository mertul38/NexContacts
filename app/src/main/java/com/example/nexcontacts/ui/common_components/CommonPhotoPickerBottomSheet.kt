package com.example.nexcontacts.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonPhotoPickerBottomSheet(
    onCamera: () -> Unit,
    onGallery: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SheetButton("📷  Camera") { onCamera() }
        Spacer(modifier = Modifier.height(12.dp))

        SheetButton("🖼️  Gallery") { onGallery() }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Cancel",
            color = Color(0xFF2979FF),
            fontSize = 16.sp,
            modifier = Modifier.clickable { onCancel() }
        )
    }
}

@Composable
private fun SheetButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFF7F7F7), RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 16.sp)
    }
}
