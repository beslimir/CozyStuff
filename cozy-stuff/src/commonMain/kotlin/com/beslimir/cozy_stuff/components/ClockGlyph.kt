package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.beslimir.cozy_stuff.theme.Ink
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun ClockGlyph(
    modifier: Modifier = Modifier,
    strokeColor: Color = Ink
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val r = min(w, h) / 2f * 0.80f
        val cx = w / 2f
        val cy = h / 2f

        drawCircle(
            color = strokeColor,
            radius = r,
            center = Offset(cx, cy),
            style = Stroke(width = r * 0.14f)
        )

        // Hour hand at ~2 o'clock (−60°)
        val hourLen = r * 0.55f
        val hourAngle = (-60f).degToRad()
        drawLine(
            color = strokeColor,
            start = Offset(cx, cy),
            end = Offset(cx + hourLen * cos(hourAngle), cy + hourLen * sin(hourAngle)),
            strokeWidth = r * 0.14f
        )

        // Minute hand at ~6 o'clock (90°)
        val minLen = r * 0.80f
        val minAngle = 90f.degToRad()
        drawLine(
            color = strokeColor,
            start = Offset(cx, cy),
            end = Offset(cx + minLen * cos(minAngle), cy + minLen * sin(minAngle)),
            strokeWidth = r * 0.12f
        )
    }
}

private fun Float.degToRad(): Float = this * PI.toFloat() / 180f
