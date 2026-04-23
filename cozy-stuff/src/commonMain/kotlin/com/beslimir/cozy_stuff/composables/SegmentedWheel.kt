package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

private const val OUTLINE_STROKE = 1.5f
private const val OUTLINE_WOBBLE = 1.6f
private const val OUTLINE_SEED = 2024

private val UnselectedLabelColor = Ink.copy(alpha = 0.80f)
private val UnavailableLabelColor = Ink.copy(alpha = 0.45f)
private val UnavailableSegmentFill = Ink.copy(alpha = 0.18f)

private const val CURRENT_SEGMENT_FONT_SIZE = 10.5
private const val SEGMENT_FONT_SIZE = 9.5
private const val YEAR_FONT_SIZE = 22

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

@Immutable
data class WheelSegmentBuilder(
    val segment: WheelSegment,
    val startAngle: Float,
    val sweepAngle: Float,
)

/**
 * Canvas-drawn radial segment selector where each WheelSegment occupies an arc; the current
 * segment pops outward and tapping an available segment calls onSegmentClick with its id.
 */

@Composable
fun SegmentedWheel(
    segments: List<WheelSegment>,
    year: Int,
    onSegmentClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (segments.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()

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

    val rotationOffset = remember(wheelSegmentBuilderList) {
        val currentSeg = wheelSegmentBuilderList.find { it.segment.isCurrent }
        if (currentSeg != null) {
            val midAngle = currentSeg.startAngle + currentSeg.sweepAngle / 2f
            -90f - midAngle
        } else 0f
    }

    val displaySegments = remember(wheelSegmentBuilderList, rotationOffset) {
        if (rotationOffset == 0f) wheelSegmentBuilderList
        else wheelSegmentBuilderList.map { seg -> seg.copy(startAngle = seg.startAngle + rotationOffset) }
    }

    val charMeasurements = remember(displaySegments) {
        displaySegments.map { seg ->
            val isCurrent = seg.segment.isCurrent
            val labelStyle = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = if (isCurrent) CURRENT_SEGMENT_FONT_SIZE.sp else SEGMENT_FONT_SIZE.sp,
                fontWeight = if (isCurrent) FontWeight.SemiBold else FontWeight.Normal,
                color = Ink
            )
            seg.segment.label.split(" ").map { word ->
                word.map { char ->
                    textMeasurer.measure(
                        char.toString(),
                        labelStyle,
                        softWrap = false,
                        maxLines = 1
                    )
                }
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
                val innerR = minDim * 0.24f
                val outerR = minDim * 0.47f

                if (dist in innerR..outerR) {
                    val rawAngle = atan2(dy, dx) * 180f / PI.toFloat()
                    val tapAngle = (rawAngle + 90f + 360f) % 360f

                    displaySegments.forEach { seg ->
                        if (!seg.segment.isAvailable) return@forEach
                        val segmentId = seg.segment.id ?: return@forEach
                        val normStart = (seg.startAngle + 90f + 360f) % 360f
                        val normEnd = (normStart + seg.sweepAngle) % 360f
                        val inArc = if (normStart <= normEnd) {
                            tapAngle in normStart..normEnd
                        } else {
                            tapAngle >= normStart || tapAngle <= normEnd
                        }
                        if (inArc) {
                            onSegmentClick(segmentId)
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

        val innerR = minDim * 0.24f
        val normalOuterR = minDim * 0.42f
        val popOutOuterR = minDim * 0.47f
        val popOutOffset = minDim * 0.025f

        drawCircle(
            color = Color.Black.copy(alpha = 0.06f),
            radius = normalOuterR,
            center = Offset(cx + 2f, cy + 3f)
        )

        drawCircle(
            color = Linen,
            radius = normalOuterR + 6f,
            center = Offset(cx, cy)
        )

        displaySegments.forEachIndexed { index, seg ->
            val isAvailable = seg.segment.isAvailable
            val isCurrent = seg.segment.isCurrent

            val fillColor = if (isAvailable) seg.segment.color else UnavailableSegmentFill

            val midAngle = seg.startAngle + seg.sweepAngle / 2f
            val midRad = degToRad(midAngle)
            val outerR = if (isCurrent) popOutOuterR else normalOuterR
            val popX = if (isCurrent) cos(midRad) * popOutOffset else 0f
            val popY = if (isCurrent) sin(midRad) * popOutOffset else 0f
            val ringWidth = outerR - innerR
            val midR = (innerR + outerR) / 2f

            drawArc(
                color = fillColor,
                startAngle = seg.startAngle,
                sweepAngle = seg.sweepAngle,
                useCenter = false,
                topLeft = Offset(cx - midR + popX, cy - midR + popY),
                size = Size(midR * 2, midR * 2),
                style = Stroke(width = ringWidth, cap = StrokeCap.Butt)
            )

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

            drawSegmentInkOutline(
                center = Offset(cx + popX, cy + popY),
                innerR = innerR,
                outerR = outerR,
                startAngle = seg.startAngle,
                sweepAngle = seg.sweepAngle,
                ink = Ink
            )

            val labelColor = when {
                isCurrent -> seg.segment.onColor
                isAvailable -> UnselectedLabelColor
                else -> UnavailableLabelColor
            }
            val wordResults = charMeasurements[index]
            val wordCount = wordResults.size
            val lineSpacing =
                wordResults.firstOrNull()?.firstOrNull()?.size?.height?.toFloat() ?: 0f
            val normalizedMid = ((midAngle + 90f) % 360f + 360f) % 360f
            val isTopHalf = normalizedMid <= 90f || normalizedMid >= 270f

            val labelPadding = lineSpacing * 0.3f

            wordResults.forEachIndexed { wordIdx, charResults ->
                val wordLabelR = if (isCurrent) {
                    outerR - (wordIdx + 0.5f) * lineSpacing - labelPadding
                } else {
                    val rowFromRing = if (isTopHalf) wordCount - 1 - wordIdx else wordIdx
                    outerR + (rowFromRing + 0.5f) * lineSpacing + labelPadding
                }
                val totalWidth = charResults.sumOf { it.size.width }.toFloat()
                val totalArcAngleRad = totalWidth / wordLabelR
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
                    val charAngleRad =
                        startAngleRad + angleSign * (cumWidth + charW / 2f) / wordLabelR
                    val charX = cx + cos(charAngleRad) * wordLabelR + popX
                    val charY = cy + sin(charAngleRad) * wordLabelR + popY
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
        }

        drawCircle(
            color = Linen,
            radius = innerR * 0.92f,
            center = Offset(cx, cy)
        )
        drawJitterCircle(
            center = Offset(cx, cy),
            radius = innerR * 0.92f,
            color = Ink,
            strokeWidth = OUTLINE_STROKE,
            wobble = OUTLINE_WOBBLE,
            seed = OUTLINE_SEED + 7
        )

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

private fun degToRad(deg: Float): Float = deg * PI.toFloat() / 180f

private fun DrawScope.drawSegmentInkOutline(
    center: Offset,
    innerR: Float,
    outerR: Float,
    startAngle: Float,
    sweepAngle: Float,
    ink: Color
) {
    val outerPath =
        jitteredArcPath(
            center, outerR, startAngle, sweepAngle,
            OUTLINE_WOBBLE,
            OUTLINE_SEED
        )
    drawPath(
        path = outerPath,
        color = ink,
        style = Stroke(width = OUTLINE_STROKE, pathEffect = PathEffect.cornerPathEffect(6f))
    )

    val innerPath =
        jitteredArcPath(
            center, innerR, startAngle, sweepAngle,
            OUTLINE_WOBBLE, OUTLINE_SEED + 3
        )
    drawPath(
        path = innerPath,
        color = ink,
        style = Stroke(width = OUTLINE_STROKE, pathEffect = PathEffect.cornerPathEffect(6f))
    )

    val startLine = jitteredLinePath(
        from = polarToCartesian(center, innerR, startAngle),
        to = polarToCartesian(center, outerR, startAngle),
        wobble = OUTLINE_WOBBLE,
        seed = OUTLINE_SEED + 5
    )
    drawPath(path = startLine, color = ink, style = Stroke(width = OUTLINE_STROKE))

    val endAngle = startAngle + sweepAngle
    val endLine = jitteredLinePath(
        from = polarToCartesian(center, innerR, endAngle),
        to = polarToCartesian(center, outerR, endAngle),
        wobble = OUTLINE_WOBBLE,
        seed = OUTLINE_SEED + 6
    )
    drawPath(path = endLine, color = ink, style = Stroke(width = OUTLINE_STROKE))
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
    samples: Int = 72
): Path {
    val rnd = Random(seed)
    fun j() = (rnd.nextFloat() - 0.5f) * 2f * wobble
    val p = Path()
    for (i in 0..samples) {
        val t = i / samples.toFloat()
        val ang = startAngle + sweepAngle * t
        val a = degToRad(ang)
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
    samples: Int = 16
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
    samples: Int = 100
) {
    val path = jitteredArcPath(center, radius, 0f, 360f, wobble, seed, samples)
    drawPath(
        path = path,
        color = color,
        style = Stroke(width = strokeWidth, pathEffect = PathEffect.cornerPathEffect(8f))
    )
}
