package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.DailyMessageCard
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun DailyMessageCardPreview() {
    ParchmentTheme {
        DailyMessageCard(
            title = "A Spring Morning",
            verse = "Still waters run deep, and so do quiet mornings.",
            onClick = {},
            onShare = {},
            onBookmark = {},
            onDelete = {}
        )
    }
}
