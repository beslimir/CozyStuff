package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.components.SegmentedWheel
import com.beslimir.cozy_stuff.components.WheelSegment
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.theme.SeasonCream
import com.beslimir.cozy_stuff.theme.SeasonGold
import com.beslimir.cozy_stuff.theme.SeasonGreen
import com.beslimir.cozy_stuff.theme.SeasonPurple
import com.beslimir.cozy_stuff.theme.SeasonViolet
import com.beslimir.cozy_stuff.theme.White

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
