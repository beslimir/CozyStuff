package com.beslimir.cozy_stuff.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// You can swap to your custom font family if you have one.
// Using system 'serif' for a classic, warm feel.
private val Serif = FontFamily.Serif

val AppTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Serif,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp
    )
)