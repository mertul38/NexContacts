package com.example.nexcontacts.ui.add_contact

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController

@Composable
fun SuccessScreen(navController: NavController) {

    // 2 saniye bekle → Contacts'a dön
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("contacts") {
            popUpTo("contacts") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Successful",
            fontSize = 28.sp
        )
    }
}
