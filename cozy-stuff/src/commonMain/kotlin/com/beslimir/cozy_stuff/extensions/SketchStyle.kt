package com.beslimir.cozy_stuff.extensions

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.theme.Black
import kotlin.math.ceil
import kotlin.math.min
import kotlin.random.Random

@Immutable
data class SketchStyle(
    val strokeWidth: Dp = 1.5.dp,
    val wobblePx: Float = 1.8f,
    val cornerRadius: Dp = 8.dp,
    val shadowAlpha: Float = 0.08f,
    val noiseAlpha: Float = 0.06f,
    val seed: Int = 1337
)

fun DrawScope.drawParchmentSurface(
    bgColor: Color,
    inkColor: Color,
    style: SketchStyle,
    noise: ImageBitmap? = null
) {
    val r = style.cornerRadius.toPx()
    val strokeWidthPx = style.strokeWidth.toPx()

    // Soft offset shadow
    drawRoundRect(
        color = Black.copy(alpha = style.shadowAlpha),
        topLeft = Offset(1.5f, 2.5f),
        size = Size(size.width - 1.5f, size.height - 2.5f),
        cornerRadius = CornerRadius(r, r)
    )

    // Background parchment color
    drawRoundRect(
        color = bgColor,
        cornerRadius = CornerRadius(r, r)
    )

    // Sketchy ink border
    val path = jitteredRoundRectPath(
        width = size.width,
        height = size.height,
        radius = r,
        wobble = style.wobblePx,
        seed = style.seed
    )
    drawPath(
        path = path,
        color = inkColor,
        style = Stroke(
            width = strokeWidthPx,
            join = StrokeJoin.Round,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.cornerPathEffect(r)
        )
    )

    // Optional paper noise tiled across the surface
    if (noise != null) {
        val cols = ceil(size.width / noise.width.toFloat()).toInt()
        val rows = ceil(size.height / noise.height.toFloat()).toInt()
        for (y in 0 until rows) {
            for (x in 0 until cols) {
                drawImage(
                    image = noise,
                    topLeft = Offset(
                        x * noise.width.toFloat(),
                        y * noise.height.toFloat()
                    ),
                    alpha = style.noiseAlpha
                )
            }
        }
    }
}

private fun jitteredRoundRectPath(
    width: Float,
    height: Float,
    radius: Float,
    wobble: Float,
    seed: Int,
    samplesPerSide: Int = 28
): Path {
    val rnd = Random(seed)
    val path = Path()
    fun j(delta: Float) = (rnd.nextFloat() - 0.5f) * 2f * delta

    val left = 0f
    val top = 0f
    val right = width
    val bottom = height
    val r = min(radius, min(width, height) / 2f)

    fun sampleEdge(x0: Float, y0: Float, x1: Float, y1: Float, first: Boolean = false) {
        for (i in 0..samplesPerSide) {
            val t = i / samplesPerSide.toFloat()
            val x = x0 + (x1 - x0) * t + j(wobble)
            val y = y0 + (y1 - y0) * t + j(wobble)
            if (i == 0 && first) path.moveTo(x, y) else path.lineTo(x, y)
        }
    }

    // Walk the rectangle edges with jitter
    sampleEdge(left + r, top, right - r, top, first = true)      // Top edge
    sampleEdge(right - r, top, right, top + r)                   // Top-right corner-ish
    sampleEdge(right, top + r, right, bottom - r)                // Right edge
    sampleEdge(right, bottom - r, right - r, bottom)             // Bottom-right corner
    sampleEdge(right - r, bottom, left + r, bottom)              // Bottom edge
    sampleEdge(left + r, bottom, left, bottom - r)               // Bottom-left corner
    sampleEdge(left, bottom - r, left, top + r)                  // Left edge
    sampleEdge(left, top + r, left + r, top)                     // Top-left corner
    path.close()
    return path
}

/**
 * Modifier to draw the parchment background and sketch border behind content.
 */
fun Modifier.sketchParchment(
    bgColor: Color,
    inkColor: Color,
    style: SketchStyle = SketchStyle(),
    noise: ImageBitmap? = null
): Modifier = this.then(
    Modifier.drawBehind {
        drawParchmentSurface(bgColor = bgColor, inkColor = inkColor, style = style, noise = noise)
    }
)