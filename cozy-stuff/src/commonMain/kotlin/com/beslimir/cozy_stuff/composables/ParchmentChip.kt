package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.extensions.SketchStyle
import com.beslimir.cozy_stuff.extensions.sketchParchment
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Pill-shaped tag or filter chip with a sketchy border; toggles between an Olive fill when
 * selected and the default Linen background when unselected.
 */

@Composable
fun ParchmentChip(
    label: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    inkColor: Color = Ink,
    selectedContainerColor: Color = Olive,
    selectedContentColor: Color = Linen,
    bgColor: Color = Linen,
) {
    val spacing = LocalSpacing.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(AppShapes.extraLarge)
            .sketchParchment(
                bgColor = if (isSelected) selectedContainerColor else bgColor,
                inkColor = inkColor,
                style = SketchStyle(cornerRadius = spacing.xLarge)
            )
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = spacing.medium, vertical = spacing.xSmall)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) selectedContentColor else inkColor
        )
    }
}
