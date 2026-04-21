package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.ClayLight45
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun ParchmentProgressLine(
    current: Int,
    total: Int,
    modifier: Modifier = Modifier,
    height: Dp = LocalSpacing.current.small,
    showLabel: Boolean = true,
    trackColor: Color = ClayLight45,
    fillColor: Color = Olive,
    inkColor: Color = Ink
) {
    val spacing = LocalSpacing.current
    val progress = if (total > 0) (current.toFloat() / total.toFloat()).coerceIn(0f, 1f) else 0f

    Column {
        Canvas(
            modifier = modifier.height(height)
        ) {
            val w = size.width
            val h = size.height
            val radius = h / 2f

            // Track
            drawRoundRect(
                color = trackColor,
                size = Size(w, h),
                cornerRadius = CornerRadius(radius, radius)
            )

            // Ink outline (slightly sketchy using corner path effect)
            drawRoundRect(
                color = inkColor,
                size = Size(w, h),
                cornerRadius = CornerRadius(radius, radius),
                style = Stroke(width = 1.5f, pathEffect = PathEffect.cornerPathEffect(radius))
            )

            // Fill
            val fillW = w * progress
            if (fillW > 0f) {
                drawRoundRect(
                    color = fillColor,
                    size = Size(fillW, h),
                    cornerRadius = CornerRadius(radius, radius)
                )
            }

            // Accent dot at far right of track (like the reference)
            val dotRadius = h * 0.25f
            drawCircle(
                color = trackColor,
                radius = dotRadius,
                center = Offset(w - dotRadius - 1.5f, h / 2f)
            )
        }

        if (showLabel) {
            Spacer(Modifier.height(spacing.xxSmall))
            Text(
                text = "$current / $total",
                style = MaterialTheme.typography.bodyMedium,
                color = Ink
            )
        }
    }
}
