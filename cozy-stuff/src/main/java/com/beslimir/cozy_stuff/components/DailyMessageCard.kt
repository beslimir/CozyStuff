package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun DailyMessageCard(
    title: String,
    verse: String,
    onClick: () -> Unit,
    onShare: () -> Unit,
    onBookmark: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = Olive,
    iconTintColor: Color = Linen,
    textColor: Color = Ink,
    iconSize: Dp = LocalSpacing.current.xxxLarge
) {
    val spacing = LocalSpacing.current
    var menuExpanded by remember { mutableStateOf(false) }

    ParchmentSurface(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "Daily message card" }
            .clickable { onClick() },
        contentPadding = spacing.large
    ) {
        Row(verticalAlignment = Alignment.Top) {
            // Olive emblem
            Box(
                modifier = Modifier
                    .size(iconSize)
                    .clip(AppShapes.small)
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Eco,
                    contentDescription = "Leaf",
                    tint = iconTintColor
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = spacing.medium)
                    .weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = textColor
                    )
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Menu",
                                tint = textColor
                            )
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Share") },
                                onClick = { menuExpanded = false; onShare() }
                            )
                            DropdownMenuItem(
                                text = { Text("Bookmark") },
                                onClick = { menuExpanded = false; onBookmark() }
                            )
                            DropdownMenuItem(
                                text = { Text("Delete") },
                                onClick = { menuExpanded = false; onDelete() }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(spacing.small))

                Text(
                    text = verse,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor
                )
            }
        }
    }
}

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