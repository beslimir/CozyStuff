package com.beslimir.cozy_stuff.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.tokens.LocalSpacing
import com.beslimir.cozy_stuff.tokens.Spacing

private val LightColors = lightColorScheme(
    primary = Olive,
    onPrimary = Color.White,
    secondary = Clay,
    onSecondary = Ink,
    background = Linen,
    onBackground = Ink,
    surface = Linen,
    onSurface = Ink,
    surfaceVariant = DisabledSurface,
    onSurfaceVariant = Ink
)

@Composable
fun ParchmentTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(), // You can ignore dark for a warm-toned app if you want
    colorScheme: ColorScheme = LightColors,
    content: @Composable () -> Unit
) {
// For this style, we keep light parchment even in dark mode.
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}

// Defaults for cards in this style (optional helper)
val ParchmentCardDefaults
    @Composable get() = CardDefaults.cardColors(
        containerColor = Linen,
        contentColor = Ink
    )