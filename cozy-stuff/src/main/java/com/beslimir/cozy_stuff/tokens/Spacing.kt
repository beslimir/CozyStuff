package com.beslimir.cozy_stuff.tokens

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val zero: Dp = 0.dp,
    val xxxSmall: Dp = 2.dp,
    val xxSmall: Dp = 4.dp,
    val xSmall: Dp = 6.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xLarge: Dp = 24.dp,
    val xxLarge: Dp = 32.dp,
    val xxxLarge: Dp = 40.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }