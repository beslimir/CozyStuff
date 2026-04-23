package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.components.LeafGlyph
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Full-screen empty-state layout with a LeafGlyph, headline, and optional description;
 * use as a screen-level placeholder when a list has no items.
 */

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    title: String = "Nothing Here Yet",
    description: String? = null,
    iconSize: Dp = 80.dp,
    inkColor: Color = Ink,
    mutedColor: Color = Ink60
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.xLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        LeafGlyph(
            modifier = Modifier.size(iconSize),
            strokeColor = inkColor
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = inkColor,
            textAlign = TextAlign.Center
        )

        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = mutedColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

