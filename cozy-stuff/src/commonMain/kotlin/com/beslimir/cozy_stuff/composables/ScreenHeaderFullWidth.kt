package com.beslimir.cozy_stuff.composables

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Full-bleed screen header showing a title, period label, description, progress bar,
 * and back navigation; designed for series or course detail screens.
 */

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
    ) {
        Column(
            modifier = Modifier.padding(horizontal = spacing.xLarge, vertical = spacing.large)
        ) {
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

        ParchmentDivider(color = dividerColor, strokeWidth = dividerStrokeWidth)
    }
}
