package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.ListItemCard
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ListItemCardPreview() {
    ParchmentTheme {
        var bookmarked by remember { mutableStateOf(false) }
        ListItemCard(
            title = "Spring Season",
            subtitle = "Apr 5 – Jun 24 · Green",
            time = "10:30 AM",
            isBookmarked = bookmarked,
            onBookmarkToggle = { bookmarked = !bookmarked },
            onClick = {}
        )
    }
}
