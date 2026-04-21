package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.MessageCard
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun MessageCardPreview() {
    ParchmentTheme {
        var bookmarked by remember { mutableStateOf(false) }

        MessageCard(
            header = "Today's Reflection",
            title = "A Spring Morning",
            subtitle = "Subtitle",
            verse = "Still waters run deep, and so do quiet mornings filled with grace and stillness beyond all understanding.",
            isBookmarked = bookmarked,
            onBookmarkToggle = { bookmarked = !bookmarked },
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageCardMinimalPreview() {
    ParchmentTheme {
        MessageCard(
            title = "A Spring Morning",
            verse = "Still waters run deep, and so do quiet mornings.",
            isBookmarked = true,
            onBookmarkToggle = {},
            onClick = {}
        )
    }
}
