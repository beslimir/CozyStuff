package com.beslimir.cozy_stuff.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Olive

@Composable
fun FavoriteToggle(
    isBookmarked: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = Olive,
    inactiveColor: Color = Ink60,
    contentDescriptionBookmarked: String = "Remove from favorites",
    contentDescriptionUnbookmarked: String = "Add to favorites",
) {
    IconButton(
        onClick = onToggle,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isBookmarked) contentDescriptionBookmarked else contentDescriptionUnbookmarked,
            tint = if (isBookmarked) activeColor else inactiveColor
        )
    }
}
