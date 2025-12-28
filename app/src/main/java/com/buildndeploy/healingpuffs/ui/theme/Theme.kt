package com.buildndeploy.healingpuffs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = NetflixRed,
    background = NetflixBlack,
    surface = NetflixDarkGrey,
    onPrimary = TextWhite,
    onBackground = TextWhite,
    onSurface = TextGrey,
    secondary = NetflixGrey
)

@Composable
fun HealingPuffsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
