package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.beslimir.cozy_stuff.theme.Clay
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Floating action button with a sketchy ParchmentSurface border and a customizable icon;
 * use for the primary screen-level action such as compose or add.
 */

@Composable
fun ParchmentFab(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Clay,
    iconTintColor: Color = Linen,
    inkColor: Color = Ink,
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier,
        bgColor = containerColor,
        inkColor = inkColor,
        contentPadding = spacing.small
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(spacing.xxxLarge)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconTintColor
            )
        }
    }
}
