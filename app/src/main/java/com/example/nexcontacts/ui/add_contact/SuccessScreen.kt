package com.example.nexcontacts.ui.add_contact

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("contacts") {
            popUpTo("contacts") { inclusive = true }
        }
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.done)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 2.0f
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "All Done!",
                style = AppTheme.typography.headlineLarge,
                color = AppTheme.colors.textFifth
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "New contact saved \uD83C\uDF89",
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.textSecondary
            )
        }
    }
}
