package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Linen90
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun MessageListItem(
    index: Int,
    title: String,
    date: String,
    reference: String,
    delivered: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    indexBackgroundColor: Color = Linen90,
    indexTextColor: Color = Olive,
    textColor: Color = Ink,
    deliveredIconColor: Color = Olive,
    pendingDotColor: Color = Ink60,
    indexBoxSize: Dp = LocalSpacing.current.xxLarge,
    statusDotSize: Dp = LocalSpacing.current.small
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        contentPadding = spacing.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading index circle
            Box(
                modifier = Modifier
                    .size(indexBoxSize)
                    .clip(AppShapes.small)
                    .background(indexBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = index.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = indexTextColor
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = spacing.medium)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(spacing.xxSmall))
                Text(
                    text = "$date · $reference",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor
                )
            }

            // Status + chevron
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (delivered) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Received",
                        tint = deliveredIconColor
                    )
                } else {
                    // small muted dot
                    Box(
                        modifier = Modifier
                            .size(statusDotSize)
                            .background(pendingDotColor, shape = AppShapes.extraSmall)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Open",
                    tint = textColor
                )
            }
        }
    }
}

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