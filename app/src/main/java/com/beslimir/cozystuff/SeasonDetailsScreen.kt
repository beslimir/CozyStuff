package com.beslimir.cozystuff

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.MessageListItem
import com.beslimir.cozy_stuff.composables.ScreenHeaderFullWidth
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

data class SeasonMessage(
    val index: Int,
    val title: String,
    val date: String,
    val reference: String,
    val delivered: Boolean
)

@Composable
fun SeasonDetailScreen(
    seasonTitle: String,
    period: String,
    description: String,
    current: Int,
    total: Int,
    messages: List<SeasonMessage>,
    onMessageClick: (SeasonMessage) -> Unit,
    onBack: () -> Unit
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(spacing.large)
    ) {
        item(key = "header") {
            ScreenHeaderFullWidth(
                title = seasonTitle,
                period = period,
                description = description,
                current = current,
                total = total,
                onBack = onBack
            )
            Spacer(modifier = Modifier.height(spacing.medium))
        }

        items(messages, key = { it.index }) { msg ->
            MessageListItem(
                index = msg.index,
                title = msg.title,
                date = msg.date,
                reference = msg.reference,
                delivered = msg.delivered,
                onClick = { onMessageClick(msg) },
                modifier = Modifier
                    .padding(horizontal = spacing.xLarge)
            )
        }

        item {
            Spacer(modifier = Modifier.height(spacing.xLarge))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SeasonDetailScreenPreview() {
    val sample = List(12) { i ->
        SeasonMessage(
            index = 12 - i,
            title = listOf(
                "A Morning of Stillness",
                "The Art of Slowing Down",
                "Finding Your Rhythm",
                "Moments of Gratitude",
                "Seasons of Change",
                "The Value of Quiet",
                "Walking Mindfully",
                "Embracing the Present",
                "At the End of the Day"
            )[i % 9],
            date = "2026-04-${(16 - i).coerceAtLeast(1).toString().padStart(2, '0')}",
            reference = listOf(
                "Entry 1",
                "Entry 2",
                "Entry 3",
                "Entry 4",
                "Entry 5",
                "Entry 6",
                "Entry 7",
                "Entry 8",
                "Entry 9"
            )[i % 9],
            delivered = i % 3 == 0
        )
    }

    ParchmentTheme {
        SeasonDetailScreen(
            seasonTitle = "Spring Season 2026",
            period = "Apr 5 – Jun 24 · Green",
            description = "Spring – Fifty Days of Renewal and Growth",
            current = 12,
            total = 50,
            messages = sample,
            onMessageClick = {},
            onBack = {},
        )
    }
}