package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Immutable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.beslimir.cozy_stuff.theme.Black06
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink18
import com.beslimir.cozy_stuff.theme.Ink45
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Ink80
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.SeasonCream
import com.beslimir.cozy_stuff.theme.SeasonGold
import com.beslimir.cozy_stuff.theme.SeasonGreen
import com.beslimir.cozy_stuff.theme.SeasonPurple
import com.beslimir.cozy_stuff.theme.SeasonViolet
import com.beslimir.cozy_stuff.theme.White
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


// Styling knobs
private const val OUTLINE_STROKE = 1.5f
private const val OUTLINE_WOBBLE = 1.6f
private const val OUTLINE_SEED = 2024

private const val CURRENT_SEASON_FONT_SIZE = 10.5
private const val SEASON_FONT_SIZE = 9.5
private const val YEAR_FONT_SIZE = 22

/**
 * A single segment of the wheel. Colors and label are fully supplied by the caller.
 *
 * @param id       Opaque identifier returned by [SeasonWheel]'s onSeasonClick. Null makes the
 *                 segment non-tappable.
 * @param label    Text drawn along the arc (uppercase recommended).
 * @param color    Fill color of the segment when [isAvailable] is true.
 * @param onColor  Label color used when this segment [isCurrent].
 * @param sweepFraction Fraction of the full circle (0f–1f). All segments should sum to 1f.
 * @param isAvailable   Whether the segment is interactive and drawn in its own [color].
 * @param isCurrent     Whether this is the active segment (popped out, highlighted).
 */
@Immutable
data class WheelSegment(
    val id: String?,
    val label: String,
    val color: Color,
    val onColor: Color,
    val sweepFraction: Float,
    val isAvailable: Boolean,
    val isCurrent: Boolean,
)

/**
 * Computed layout for a single segment: the source [WheelSegment] plus its resolved
 * [startAngle] and [sweepAngle] in degrees. Exposed so callers can inspect the final
 * geometry (e.g. to draw overlays or run hit tests outside the composable).
 */
@Immutable
data class WheelSegmentBuilder(
    val segment: WheelSegment,
    val startAngle: Float,
    val sweepAngle: Float,
)

@Composable
fun SegmentedWheel(
    segments: List<WheelSegment>,
    year: Int,
    onSeasonClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (segments.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()

    // Build segments with tiny gaps so ink lines read well
    val wheelSegmentBuilderList = remember(segments) {
        val gapDeg = 3f
        var currentAngle = -90f
        segments.map { segment ->
            val fullSweep = segment.sweepFraction * 360f
            val sweepAngle = (fullSweep - gapDeg).coerceAtLeast(4f)
            val startAngle = currentAngle + gapDeg / 2f
            currentAngle += fullSweep
            WheelSegmentBuilder(segment, startAngle, sweepAngle)
        }
    }

    // Rotate so current season sits at 12 o'clock
    val rotationOffset = remember(wheelSegmentBuilderList) {
        val currentSeg = wheelSegmentBuilderList.find { it.segment.isCurrent }
        if (currentSeg != null) {
            val midAngle = currentSeg.startAngle + currentSeg.sweepAngle / 2f
            -90f - midAngle
        } else 0f
    }

    val displaySegments = remember(wheelSegmentBuilderList, rotationOffset) {
        if (rotationOffset == 0f) wheelSegmentBuilderList
        else wheelSegmentBuilderList.map { seg ->
            seg.copy(startAngle = seg.startAngle + rotationOffset)
        }
    }

    // Pre-measure label characters (serif for classic feel)
    val charMeasurements = remember(displaySegments) {
        displaySegments.map { seg ->
            val isCurrent = seg.segment.isCurrent
            val labelStyle = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = if (isCurrent) CURRENT_SEASON_FONT_SIZE.sp else SEASON_FONT_SIZE.sp,
                fontWeight = if (isCurrent) FontWeight.SemiBold else FontWeight.Normal,
                color = Ink
            )
            seg.segment.label.map { char ->
                textMeasurer.measure(char.toString(), labelStyle, softWrap = false, maxLines = 1)
            }
        }
    }

    Canvas(
        modifier = modifier.pointerInput(displaySegments) {
            detectTapGestures { tap ->
                val cx = size.width / 2f
                val cy = size.height / 2f
                val dx = tap.x - cx
                val dy = tap.y - cy
                val dist = sqrt(dx * dx + dy * dy)
                val minDim = min(size.width, size.height)
                val innerR = minDim * 0.20f
                val outerR = minDim * 0.43f

                if (dist in innerR..outerR) {
                    val rawAngle = atan2(dy, dx) * 180f / PI.toFloat()
                    val tapAngle = (rawAngle + 90f + 360f) % 360f

                    displaySegments.forEach { seg ->
                        if (!seg.segment.isAvailable) return@forEach
                        val seasonId = seg.segment.id ?: return@forEach
                        val normStart = (seg.startAngle + 90f + 360f) % 360f
                        val normEnd = (normStart + seg.sweepAngle) % 360f
                        val inArc = if (normStart <= normEnd) {
                            tapAngle in normStart..normEnd
                        } else {
                            tapAngle >= normStart || tapAngle <= normEnd
                        }
                        if (inArc) {
                            onSeasonClick(seasonId)
                            return@detectTapGestures
                        }
                    }
                }
            }
        }
    ) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val minDim = size.minDimension

        // Radii
        val innerR = minDim * 0.20f
        val normalOuterR = minDim * 0.36f
        val popOutOuterR = minDim * 0.41f
        val popOutOffset = minDim * 0.03f
        val labelR = minDim * 0.395f

        // Soft shadow under the whole wheel
        drawCircle(
            color = Black06,
            radius = normalOuterR,
            center = Offset(cx + 2f, cy + 3f)
        )

        // Subtle ring behind everything
        drawCircle(
            color = Linen,
            radius = normalOuterR + 6f,
            center = Offset(cx, cy)
        )

        displaySegments.forEachIndexed { index, seg ->
            val isAvailable = seg.segment.isAvailable
            val isCurrent = seg.segment.isCurrent

            val fillColor = if (isAvailable) seg.segment.color else Ink18

            val midAngle = seg.startAngle + seg.sweepAngle / 2f
            val midRad = degToRad(midAngle)
            val outerR = if (isCurrent) popOutOuterR else normalOuterR
            val popX = if (isCurrent) cos(midRad) * popOutOffset else 0f
            val popY = if (isCurrent) sin(midRad) * popOutOffset else 0f
            val ringWidth = outerR - innerR
            val midR = (innerR + outerR) / 2f

            // Segment fill
            drawArc(
                color = fillColor,
                startAngle = seg.startAngle,
                sweepAngle = seg.sweepAngle,
                useCenter = false,
                topLeft = Offset(cx - midR + popX, cy - midR + popY),
                size = Size(midR * 2, midR * 2),
                style = Stroke(width = ringWidth, cap = StrokeCap.Butt)
            )

            // Inner highlight on the current season
            if (isCurrent) {
                drawArc(
                    color = fillColor.copy(alpha = 0.28f),
                    startAngle = seg.startAngle,
                    sweepAngle = seg.sweepAngle,
                    useCenter = false,
                    topLeft = Offset(cx - innerR + popX, cy - innerR + popY),
                    size = Size(innerR * 2, innerR * 2),
                    style = Stroke(width = innerR * 0.22f, cap = StrokeCap.Butt)
                )
            }

            // Sketchy ink outline
            drawSegmentInkOutline(
                center = Offset(cx + popX, cy + popY),
                innerR = innerR,
                outerR = outerR,
                startAngle = seg.startAngle,
                sweepAngle = seg.sweepAngle,
                ink = Ink
            )

            // Label color: onColor for current, Ink80 for available, Ink45 for unavailable
            val labelColor = when {
                isCurrent -> seg.segment.onColor
                isAvailable -> Ink80
                else -> Ink45
            }
            val charResults = charMeasurements[index]

            // Place characters along the arc
            val normalizedMid = ((midAngle + 90f) % 360f + 360f) % 360f
            val isTopHalf = normalizedMid <= 90f || normalizedMid >= 270f
            val totalWidth = charResults.sumOf { it.size.width }.toFloat()
            val totalArcAngleRad = totalWidth / labelR
            val startAngleRad: Float
            val angleSign: Float
            if (isTopHalf) {
                startAngleRad = midRad - totalArcAngleRad / 2f
                angleSign = 1f
            } else {
                startAngleRad = midRad + totalArcAngleRad / 2f
                angleSign = -1f
            }

            var cumWidth = 0f
            charResults.forEach { charResult ->
                val charW = charResult.size.width.toFloat()
                val charH = charResult.size.height.toFloat()
                val charAngleRad = startAngleRad + angleSign * (cumWidth + charW / 2f) / labelR
                val charX = cx + cos(charAngleRad) * labelR + popX
                val charY = cy + sin(charAngleRad) * labelR + popY
                val charAngleDeg = charAngleRad * 180f / PI.toFloat()
                val charRotation = if (isTopHalf) charAngleDeg + 90f else charAngleDeg - 90f

                rotate(charRotation, pivot = Offset(charX, charY)) {
                    drawText(
                        textLayoutResult = charResult,
                        color = labelColor,
                        topLeft = Offset(charX - charW / 2f, charY - charH / 2f)
                    )
                }
                cumWidth += charW
            }
        }

        // Center hole and ink ring
        drawCircle(color = Linen, radius = innerR * 0.92f, center = Offset(cx, cy))
        drawJitterCircle(
            center = Offset(cx, cy),
            radius = innerR * 0.92f,
            color = Ink,
            strokeWidth = OUTLINE_STROKE,
            wobble = OUTLINE_WOBBLE,
            seed = OUTLINE_SEED + 7
        )

        // Decorative thin ring around the wheel
        drawJitterCircle(
            center = Offset(cx, cy),
            radius = normalOuterR + 2f,
            color = Ink60,
            strokeWidth = 1.2f,
            wobble = OUTLINE_WOBBLE * 0.8f,
            seed = OUTLINE_SEED + 11
        )

        // Year in the center
        val yearStyle = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = YEAR_FONT_SIZE.sp,
            fontWeight = FontWeight.Bold,
            color = Ink
        )
        val measuredYear = textMeasurer.measure(year.toString(), yearStyle)
        drawText(
            textLayoutResult = measuredYear,
            topLeft = Offset(
                cx - measuredYear.size.width / 2f,
                cy - measuredYear.size.height / 2f
            )
        )
    }
}

/* ---------- Sketch helpers for inked outlines ---------- */

private fun degToRad(deg: Float): Float = deg * PI.toFloat() / 180f

private fun DrawScope.drawSegmentInkOutline(
    center: Offset,
    innerR: Float,
    outerR: Float,
    startAngle: Float,
    sweepAngle: Float,
    ink: Color
) {
    val outerPath = jitteredArcPath(center, outerR, startAngle, sweepAngle, OUTLINE_WOBBLE, OUTLINE_SEED)
    drawPath(outerPath, ink, style = Stroke(OUTLINE_STROKE, pathEffect = PathEffect.cornerPathEffect(6f)))

    val innerPath = jitteredArcPath(center, innerR, startAngle, sweepAngle, OUTLINE_WOBBLE, OUTLINE_SEED + 3)
    drawPath(innerPath, ink, style = Stroke(OUTLINE_STROKE, pathEffect = PathEffect.cornerPathEffect(6f)))

    val startLine = jitteredLinePath(
        polarToCartesian(center, innerR, startAngle),
        polarToCartesian(center, outerR, startAngle),
        OUTLINE_WOBBLE, OUTLINE_SEED + 5
    )
    drawPath(startLine, ink, style = Stroke(OUTLINE_STROKE))

    val endLine = jitteredLinePath(
        polarToCartesian(center, innerR, startAngle + sweepAngle),
        polarToCartesian(center, outerR, startAngle + sweepAngle),
        OUTLINE_WOBBLE, OUTLINE_SEED + 6
    )
    drawPath(endLine, ink, style = Stroke(OUTLINE_STROKE))
}

private fun polarToCartesian(center: Offset, radius: Float, angleDeg: Float): Offset {
    val a = degToRad(angleDeg)
    return Offset(center.x + cos(a) * radius, center.y + sin(a) * radius)
}

private fun jitteredArcPath(
    center: Offset,
    radius: Float,
    startAngle: Float,
    sweepAngle: Float,
    wobble: Float,
    seed: Int,
    samples: Int = 72,
): Path {
    val rnd = Random(seed)
    fun j() = (rnd.nextFloat() - 0.5f) * 2f * wobble
    val p = Path()
    for (i in 0..samples) {
        val a = degToRad(startAngle + sweepAngle * (i / samples.toFloat()))
        val x = center.x + cos(a) * (radius + j())
        val y = center.y + sin(a) * (radius + j())
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
    }
    return p
}

private fun jitteredLinePath(
    from: Offset,
    to: Offset,
    wobble: Float,
    seed: Int,
    samples: Int = 16,
): Path {
    val rnd = Random(seed)
    fun j() = (rnd.nextFloat() - 0.5f) * 2f * wobble
    val p = Path()
    for (i in 0..samples) {
        val t = i / samples.toFloat()
        val x = from.x + (to.x - from.x) * t + j()
        val y = from.y + (to.y - from.y) * t + j()
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
    }
    return p
}

private fun DrawScope.drawJitterCircle(
    center: Offset,
    radius: Float,
    color: Color,
    strokeWidth: Float,
    wobble: Float,
    seed: Int,
    samples: Int = 100,
) {
    val path = jitteredArcPath(center, radius, 0f, 360f, wobble, seed, samples)
    drawPath(path, color, style = Stroke(strokeWidth, pathEffect = PathEffect.cornerPathEffect(8f)))
}

@Preview(showBackground = true)
@Composable
private fun SegmentedWheelPreview() {
    ParchmentTheme {
        SegmentedWheel(
            year = 2026,
            onSeasonClick = {},
            modifier = Modifier.size(280.dp),
            segments = listOf(
                WheelSegment(
                    id = "winter",
                    label = "WINTER",
                    color = SeasonPurple,
                    onColor = White,
                    sweepFraction = 0.10f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "new_year",
                    label = "NEW YEAR",
                    color = SeasonGold,
                    onColor = Ink,
                    sweepFraction = 0.05f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "spring",
                    label = "SPRING",
                    color = SeasonGreen,
                    onColor = White,
                    sweepFraction = 0.20f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "midsummer",
                    label = "MIDSUMMER",
                    color = SeasonViolet,
                    onColor = White,
                    sweepFraction = 0.10f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "harvest",
                    label = "HARVEST",
                    color = SeasonCream,
                    onColor = Ink,
                    sweepFraction = 0.15f,
                    isAvailable = true,
                    isCurrent = true
                ),
                WheelSegment(
                    id = "autumn",
                    label = "AUTUMN",
                    color = SeasonGreen,
                    onColor = White,
                    sweepFraction = 0.40f,
                    isAvailable = false,
                    isCurrent = false
                )
            )
        )
    }
}
