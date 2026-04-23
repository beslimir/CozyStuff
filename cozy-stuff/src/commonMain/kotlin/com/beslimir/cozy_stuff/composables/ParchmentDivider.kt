package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.tokens.LocalSpacing
import kotlin.random.Random

/**
 * Canvas-drawn horizontal rule with a subtle hand-drawn wobble; use to separate sections
 * or as a footer line under headers.
 */

@Composable
fun ParchmentDivider(
    modifier: Modifier = Modifier,
    color: Color = Ink,
    strokeWidth: Dp = LocalSpacing.current.xxxSmall,
) {
    val spacing = LocalSpacing.current
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(spacing.xSmall)
    ) {
        val steps = 140
        val wobble = 1.8f
        val rnd = Random(1337)
        val cy = size.height / 2f
        fun j() = (rnd.nextFloat() - 0.5f) * 2f * wobble
        val path = Path().apply {
            moveTo(0f, cy + j())
            for (i in 1..steps) {
                lineTo(size.width * (i / steps.toFloat()), cy + j())
            }
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth.toPx(),
                pathEffect = PathEffect.cornerPathEffect(6f)
            )
        )
    }
}
