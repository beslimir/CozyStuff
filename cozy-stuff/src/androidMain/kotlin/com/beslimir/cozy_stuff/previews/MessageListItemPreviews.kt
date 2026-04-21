package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.MessageListItem
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun MessageListItemDeliveredPreview() {
    ParchmentTheme {
        MessageListItem(
            index = 12,
            title = "A Morning of Stillness",
            date = "2026-04-16",
            reference = "Entry 12",
            delivered = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageListItemPendingPreview() {
    ParchmentTheme {
        MessageListItem(
            index = 13,
            title = "The Road Ahead",
            date = "2026-04-17",
            reference = "Entry 13",
            delivered = false,
            onClick = {}
        )
    }
}
