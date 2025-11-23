package com.example.nexcontacts.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.nexcontacts.ui.theme.font.Mulish

data class AppTypography(
    val headlineLarge: TextStyle,
    val titleXLarge: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val labelMedSmall: TextStyle,
    val bodySmall: TextStyle
)

val LightTypography = AppTypography(
    headlineLarge = TextStyle(
        fontFamily = Mulish,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    titleXLarge = TextStyle(
        fontFamily = Mulish,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = TextStyle(
        fontFamily = Mulish,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelLarge = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    labelMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelMedSmall = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = Mulish,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
)
