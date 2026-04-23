package com.beslimir.cozy_stuff.composables

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
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
 * Feature card presenting a titled message or daily reflection with a verse excerpt, icon box,
 * and bookmark toggle; intended as the primary content card on list and detail screens.
 */

@Composable
fun MessageCard(
    title: String,
    verse: String,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    verseMaxLines: Int = 3,
    iconBackgroundColor: Color = Olive,
    iconTintColor: Color = Linen,
    textColor: Color = Ink,
    mutedColor: Color = Ink60,
    iconSize: Dp = LocalSpacing.current.xxxLarge
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "Daily message card" }
            .clickable { onClick() },
        contentPadding = spacing.large
    ) {
        Row(verticalAlignment = Alignment.Top) {
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
                        color = textColor,
                        modifier = Modifier.weight(1f)
                    )
                    FavoriteToggle(
                        isBookmarked = isBookmarked,
                        onToggle = onBookmarkToggle,
                        activeColor = iconBackgroundColor,
                        inactiveColor = mutedColor
                    )
                }

                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(spacing.xxSmall))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mutedColor
                    )
                }

                Spacer(modifier = Modifier.height(spacing.small))

                Text(
                    text = verse,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor,
                    maxLines = verseMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
