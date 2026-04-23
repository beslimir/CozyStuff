package com.beslimir.cozy_stuff.composables

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
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.components.FavoriteToggle
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Generic list-row card with a colored icon box, title, subtitle, time label, and
 * a bookmark toggle; use for browsable item lists.
 */

@Composable
fun ListItemCard(
    title: String,
    subtitle: String,
    time: String,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = Olive,
    iconTintColor: Color = Linen,
    textColor: Color = Ink,
    mutedColor: Color = Ink60,
    iconSize: Dp = LocalSpacing.current.xxxLarge
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier.fillMaxWidth(),
        contentPadding = spacing.medium
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick() },
                verticalAlignment = Alignment.Top
            ) {
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
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(spacing.xxSmall))
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall,
                        color = mutedColor
                    )
                }
            }

            FavoriteToggle(
                isBookmarked = isBookmarked,
                onToggle = onBookmarkToggle,
                modifier = Modifier.align(Alignment.CenterVertically),
                activeColor = iconBackgroundColor,
                inactiveColor = mutedColor
            )
        }
    }
}
