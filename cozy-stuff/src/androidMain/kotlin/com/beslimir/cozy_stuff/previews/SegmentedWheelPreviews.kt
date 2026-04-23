package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.SegmentedWheel
import com.beslimir.cozy_stuff.composables.WheelSegment
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.theme.SegmentCream
import com.beslimir.cozy_stuff.theme.SegmentGold
import com.beslimir.cozy_stuff.theme.SegmentGreen
import com.beslimir.cozy_stuff.theme.SegmentPurple
import com.beslimir.cozy_stuff.theme.SegmentViolet
import com.beslimir.cozy_stuff.theme.White

@Preview(showBackground = true)
@Composable
private fun SegmentedWheelPreview() {
    ParchmentTheme {
        SegmentedWheel(
            year = 2026,
            onSegmentClick = {},
            modifier = Modifier.size(280.dp),
            segments = listOf(
                WheelSegment(
                    id = "winter",
                    label = "WINTER",
                    color = SegmentPurple,
                    onColor = White,
                    sweepFraction = 0.10f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "new_year",
                    label = "NEW YEAR",
                    color = SegmentGold,
                    onColor = Ink,
                    sweepFraction = 0.05f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "spring",
                    label = "SPRING",
                    color = SegmentGreen,
                    onColor = White,
                    sweepFraction = 0.20f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "midsummer",
                    label = "MIDSUMMER",
                    color = SegmentViolet,
                    onColor = White,
                    sweepFraction = 0.10f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "harvest",
                    label = "HARVEST",
                    color = SegmentCream,
                    onColor = Ink,
                    sweepFraction = 0.15f,
                    isAvailable = true,
                    isCurrent = true
                ),
                WheelSegment(
                    id = "autumn",
                    label = "AUTUMN",
                    color = SegmentGreen,
                    onColor = White,
                    sweepFraction = 0.40f,
                    isAvailable = false,
                    isCurrent = false
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SegmentedWheelOneSeasonPreview() {
    ParchmentTheme {
        SegmentedWheel(
            year = 2026,
            onSegmentClick = {},
            modifier = Modifier.size(280.dp),
            segments = listOf(
                WheelSegment(
                    id = "spring",
                    label = "SPRING",
                    color = SegmentGreen,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = true
                ),
                WheelSegment(
                    id = "summer",
                    label = "SUMMER",
                    color = SegmentGold,
                    onColor = Ink,
                    sweepFraction = 0.25f,
                    isAvailable = false,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "autumn",
                    label = "AUTUMN",
                    color = SegmentPurple,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = false,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "winter",
                    label = "WINTER",
                    color = SegmentViolet,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = false,
                    isCurrent = false
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SegmentedWheelThreeSeasonsPreview() {
    ParchmentTheme {
        SegmentedWheel(
            year = 2026,
            onSegmentClick = {},
            modifier = Modifier.size(280.dp),
            segments = listOf(
                WheelSegment(
                    id = "spring",
                    label = "SPRING",
                    color = SegmentGreen,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "summer",
                    label = "SUMMER",
                    color = SegmentGold,
                    onColor = Ink,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "autumn",
                    label = "AUTUMN",
                    color = SegmentPurple,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = true
                ),
                WheelSegment(
                    id = "winter",
                    label = "WINTER",
                    color = SegmentViolet,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = false,
                    isCurrent = false
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SegmentedWheelAllSeasonsPreview() {
    ParchmentTheme {
        SegmentedWheel(
            year = 2026,
            onSegmentClick = {},
            modifier = Modifier.size(280.dp),
            segments = listOf(
                WheelSegment(
                    id = "spring",
                    label = "SPRING",
                    color = SegmentGreen,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "summer",
                    label = "SUMMER",
                    color = SegmentGold,
                    onColor = Ink,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "autumn",
                    label = "AUTUMN",
                    color = SegmentPurple,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = false
                ),
                WheelSegment(
                    id = "winter",
                    label = "WINTER",
                    color = SegmentViolet,
                    onColor = White,
                    sweepFraction = 0.25f,
                    isAvailable = true,
                    isCurrent = true
                )
            )
        )
    }
}
