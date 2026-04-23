package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.EmptyScreen
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun EmptyScreenPreview() {
    ParchmentTheme {
        EmptyScreen(
            title = "Nothing Here Yet",
            description = "When content arrives, it will appear here."
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyScreenTitleOnlyPreview() {
    ParchmentTheme {
        EmptyScreen(title = "No Reflections Yet")
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyScreenJourneyPreview() {
    ParchmentTheme {
        EmptyScreen(
            title = "Start Your Journey",
            description = "Create a new season to begin tracking your spiritual growth, prayer challenges, or seasonal reflections."
        )
    }
}
