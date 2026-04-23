package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.ListItemCard
import com.beslimir.cozy_stuff.composables.ParchmentCollapsingScreen
import com.beslimir.cozy_stuff.composables.ParchmentProgressLine
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

// Shows the expanded header state — what the screen looks like on first open.
@Preview(showBackground = true, heightDp = 600)
@Composable
private fun ParchmentCollapsingScreenExpandedPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        val items = (1..12).map { "Item $it" }

        ParchmentCollapsingScreen(
            title = "Spring Season",
            onBack = {},
            headerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.xLarge, vertical = spacing.large),
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    Text(
                        text = "Apr 5 – Jun 24 · Green",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Ink60
                    )
                    Text(
                        text = "Fifty days of renewal and quiet growth.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Ink
                    )
                    Spacer(modifier = Modifier.height(spacing.xxSmall))
                    ParchmentProgressLine(
                        current = 12,
                        total = 50,
                        modifier = Modifier.fillMaxWidth().height(spacing.small)
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(spacing.medium),
                verticalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                items(items) { label ->
                    var bookmarked by remember { mutableStateOf(false) }
                    ListItemCard(
                        title = label,
                        subtitle = "A short reflection for this item.",
                        time = "Monday · 9:00 AM",
                        isBookmarked = bookmarked,
                        onBookmarkToggle = { bookmarked = !bookmarked },
                        onClick = {}
                    )
                }
            }
        }
    }
}

// Shows what the screen looks like once fully collapsed (just a top bar).
@Preview(showBackground = true)
@Composable
private fun ParchmentCollapsingScreenCollapsedPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        val items = (1..12).map { "Item $it" }

        // The collapsed state is identical to ParchmentTopBar — shown here for reference.
        ParchmentCollapsingScreen(
            title = "Spring Season",
            onBack = {},
            headerContent = {}  // empty header → renders in collapsed state immediately
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    items(items) { label ->
                        var bookmarked by remember { mutableStateOf(false) }
                        ListItemCard(
                            title = label,
                            subtitle = "A short reflection for this item.",
                            time = "Monday · 9:00 AM",
                            isBookmarked = bookmarked,
                            onBookmarkToggle = { bookmarked = !bookmarked },
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}
