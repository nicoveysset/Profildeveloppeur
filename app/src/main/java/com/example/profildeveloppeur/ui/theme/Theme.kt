package com.example.profildeveloppeur.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Palette claire personnalisée (rôles Material 3 complets pour tous les composants)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1B5E8A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD2E4FF),
    onPrimaryContainer = Color(0xFF001D36),
    secondary = Color(0xFF4A6070),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD2E5F5),
    onSecondaryContainer = Color(0xFF061D2B),
    background = Color(0xFFF6F9FC),
    onBackground = Color(0xFF171C20),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF171C20),
    onSurfaceVariant = Color(0xFF41484D),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

// Palette sombre personnalisée
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8CCBFF),
    onPrimary = Color(0xFF00344F),
    primaryContainer = Color(0xFF004B71),
    onPrimaryContainer = Color(0xFFD2E4FF),
    secondary = Color(0xFFB3C8D8),
    onSecondary = Color(0xFF1D3341),
    secondaryContainer = Color(0xFF344958),
    onSecondaryContainer = Color(0xFFD2E5F5),
    background = Color(0xFF0F1418),
    onBackground = Color(0xFFDFE3E8),
    surface = Color(0xFF1A2025),
    onSurface = Color(0xFFDFE3E8),
    onSurfaceVariant = Color(0xFFC1C7CE),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)

// Formes arrondies harmonisées
private val ProfileShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun ProfilDeveloppeurTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ProfileTypography,
        shapes = ProfileShapes,
        content = content
    )
}