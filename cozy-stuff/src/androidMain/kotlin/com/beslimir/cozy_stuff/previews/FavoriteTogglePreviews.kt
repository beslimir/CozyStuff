package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.FavoriteToggle
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun FavoriteToggleBookmarkedPreview() {
    ParchmentTheme {
        FavoriteToggle(
            isBookmarked = true,
            onToggle = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteToggleUnbookmarkedPreview() {
    ParchmentTheme {
        FavoriteToggle(
            isBookmarked = false,
            onToggle = {},
        )
    }
}