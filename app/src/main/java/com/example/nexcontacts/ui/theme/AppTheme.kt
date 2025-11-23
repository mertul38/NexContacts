package com.example.nexcontacts.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalAppColors = staticCompositionLocalOf { LightColorPalette }
val LocalAppTypography = staticCompositionLocalOf { LightTypography }

object AppTheme {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
}

@Composable
fun AppTheme(
    colors: AppColors = LightColorPalette,
    typography: AppTypography = LightTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        content = content
    )
}
