package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.EmptyMessageCard
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun EmptyMessageCardPreview() {
    ParchmentTheme {
        EmptyMessageCard()
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyMessageCardCustomPreview() {
    ParchmentTheme {
        EmptyMessageCard(
            title = "Nothing Here Yet",
            message = "Check back later for new reflections"
        )
    }
}
