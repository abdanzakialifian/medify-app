package com.medify.app.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = BlueRoyalDark,
    onPrimary = White,
    primaryContainer = BlueSlate,
    secondary = BlueMidnight,
    onSecondary = White,
    secondaryContainer = BlueSlate,
    background = White,
    onBackground = BlueMidnight,
    surface = White,
    onSurface = GrayNeutral,
    outline = GrayLight,
    outlineVariant = GrayNeutral,
)

@Composable
fun MedifyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}