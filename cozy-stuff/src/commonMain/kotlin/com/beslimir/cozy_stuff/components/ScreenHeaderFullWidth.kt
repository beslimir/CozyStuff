package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing
import kotlin.random.Random

@Composable
fun ScreenHeaderFullWidth(
    title: String,
    period: String,
    description: String,
    current: Int,
    total: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Linen,
    textColor: Color = Ink,
    dividerColor: Color = Ink,
    dividerStrokeWidth: Dp = LocalSpacing.current.xxxSmall,
    progressBarHeight: Dp = LocalSpacing.current.small
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
            // Draw a hand-inked wobbly line at the bottom edge so the header blends into content
            .drawBehind {
                // Bottom ink line
                val y = size.height - 1.5f
                val steps = 140
                val wobble = 1.8f
                val rnd = Random(2026)
                fun j() = (rnd.nextFloat() - 0.5f) * 2f * wobble
                val path = Path().apply {
                    moveTo(0f, y + j())
                    for (i in 1..steps) {
                        val t = i / steps.toFloat()
                        val x = size.width * t
                        lineTo(x, y + j())
                    }
                }
                drawPath(
                    path = path,
                    color = dividerColor,
                    style = Stroke(
                        width = dividerStrokeWidth.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(6f)
                    )
                )
            }
            .padding(horizontal = spacing.xLarge, vertical = spacing.large)
    ) {
        // Top row: back arrow + title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.medium),
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = textColor
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(spacing.small))
        Text(
            text = period,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
        Spacer(modifier = Modifier.height(spacing.xxSmall))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor
        )

        Spacer(modifier = Modifier.height(spacing.medium))
        ParchmentProgressLine(
            current = current,
            total = total,
            showLabel = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(progressBarHeight)
        )
        Spacer(modifier = Modifier.height(spacing.xxSmall))
        Text(
            text = "$current of $total reflections received",
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
