package com.example.nexcontacts.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.nexcontacts.ui.navigation.AppNavHost


@Composable
fun AppRoot() {
    AppBackground {
        val navController = rememberNavController()
        AppNavHost(navController)
    }
}
