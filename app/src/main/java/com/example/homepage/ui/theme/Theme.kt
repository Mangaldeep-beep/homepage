package com.example.homepage.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFD700),      // Gold
    secondary = Color(0xFFFFA500),    // Orange
    tertiary = Color(0xFFFF8C00),     // Dark Orange
    background = Color(0xFF000000),    // Black
    surface = Color(0xFF1C1C1C),      // Dark Gray
    onPrimary = Color(0xFF000000),    // Black
    onSecondary = Color(0xFF000000),  // Black
    onTertiary = Color(0xFF000000),   // Black
    onBackground = Color(0xFFFFFFFF), // White
    onSurface = Color(0xFFFFFFFF),    // White
)

@Composable
fun HomepageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}