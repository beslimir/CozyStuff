package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.tokens.LocalSpacing

enum class MinuteInterval(val label: String, val minutes: Int) {
    EVERY_MINUTE("Every min", 1),
    EVERY_5("Every 5 min", 5),
    EVERY_15("Every 15 min", 15),
}

/**
 * Time picker with +/− increment buttons for hour and minute, an optional AM/PM chip row,
 * and a configurable minute step (1, 5, or 15 min).
 */

@Composable
fun ParchmentTimePicker(
    hour: Int,
    minute: Int,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    use24Hour: Boolean = false,
    minuteStep: MinuteInterval = MinuteInterval.EVERY_15,
    inkColor: Color = Ink,
    mutedColor: Color = Ink60,
    bgColor: Color = Linen,
    accentColor: Color = Olive,
) {
    val spacing = LocalSpacing.current

    val hourDisplay = if (use24Hour) {
        hour.toString().padStart(2, '0')
    } else {
        val h = hour % 12
        if (h == 0) "12" else h.toString()
    }
    val minuteDisplay = minute.toString().padStart(2, '0')

    ParchmentSurface(modifier = modifier, contentPadding = spacing.large, bgColor = bgColor, inkColor = inkColor) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                TimeAdjustButton(
                    symbol = "−",
                    inkColor = inkColor,
                    onClick = { onHourChange((hour - 1 + 24) % 24) },
                )
                Text(
                    text = hourDisplay,
                    style = MaterialTheme.typography.displaySmall,
                    color = inkColor,
                    modifier = Modifier.padding(horizontal = spacing.large),
                )
                TimeAdjustButton(
                    symbol = "+",
                    inkColor = inkColor,
                    onClick = { onHourChange((hour + 1) % 24) },
                )

                Text(
                    text = ":",
                    style = MaterialTheme.typography.displaySmall,
                    color = mutedColor,
                    modifier = Modifier.padding(horizontal = spacing.small),
                )

                TimeAdjustButton(
                    symbol = "−",
                    inkColor = inkColor,
                    onClick = { onMinuteChange((minute - minuteStep.minutes + 60) % 60) },
                )
                Text(
                    text = minuteDisplay,
                    style = MaterialTheme.typography.displaySmall,
                    color = inkColor,
                    modifier = Modifier.padding(horizontal = spacing.large),
                )
                TimeAdjustButton(
                    symbol = "+",
                    inkColor = inkColor,
                    onClick = { onMinuteChange((minute + minuteStep.minutes) % 60) },
                )
            }

            if (!use24Hour) {
                Spacer(modifier = Modifier.height(spacing.large))
                ParchmentDivider(color = inkColor)
                Spacer(modifier = Modifier.height(spacing.large))
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    ParchmentChip(
                        label = "AM",
                        isSelected = hour < 12,
                        onClick = {
                            if (hour >= 12) onHourChange(hour - 12)
                        },
                        inkColor = inkColor,
                        selectedContainerColor = accentColor,
                        bgColor = bgColor,
                    )
                    ParchmentChip(
                        label = "PM",
                        isSelected = hour >= 12,
                        onClick = {
                            if (hour < 12) onHourChange(hour + 12)
                        },
                        inkColor = inkColor,
                        selectedContainerColor = accentColor,
                        bgColor = bgColor,
                    )
                }
            }
        }
    }
}

@Composable
private fun TimeAdjustButton(
    symbol: String,
    inkColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(AppShapes.extraLarge)
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.medium, vertical = spacing.small),
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.headlineSmall,
            color = inkColor,
        )
    }
}
