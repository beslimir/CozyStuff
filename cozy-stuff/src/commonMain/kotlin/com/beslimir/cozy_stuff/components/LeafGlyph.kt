package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.beslimir.cozy_stuff.theme.Ink
import kotlin.math.min

@Composable
fun LeafGlyph(
    modifier: Modifier = Modifier,
    strokeColor: Color = Ink
) {
    Canvas(modifier = modifier) {
        val r = min(size.width, size.height) / 2f * 0.78f
        val cx = size.width / 2f
        val cy = size.height / 2f
        val sw = r * 0.11f
        val leafBaseY = cy + r * 0.38f

        val leaf = Path().apply {
            moveTo(cx, cy - r)
            cubicTo(cx + r * 0.75f, cy - r * 0.28f, cx + r * 0.75f, cy + r * 0.18f, cx, leafBaseY)
            cubicTo(cx - r * 0.75f, cy + r * 0.18f, cx - r * 0.75f, cy - r * 0.28f, cx, cy - r)
            close()
        }
        drawPath(leaf, color = strokeColor, style = Stroke(width = sw))

        // Midrib
        drawLine(
            color = strokeColor,
            start = Offset(cx, cy - r * 0.88f),
            end = Offset(cx, leafBaseY),
            strokeWidth = sw * 0.65f
        )

        // Stem
        drawLine(
            color = strokeColor,
            start = Offset(cx, leafBaseY),
            end = Offset(cx, cy + r * 0.88f),
            strokeWidth = sw
        )
    }
}
