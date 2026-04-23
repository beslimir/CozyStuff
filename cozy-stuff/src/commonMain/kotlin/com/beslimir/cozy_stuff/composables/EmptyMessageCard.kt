package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import com.beslimir.cozy_stuff.components.ClockGlyph
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * In-list placeholder card shown when a message is not yet available; displays a ClockGlyph with
 * a title and supporting text inside a ParchmentSurface.
 */

@Composable
fun EmptyMessageCard(
    modifier: Modifier = Modifier,
    title: String = "Not Yet Time",
    message: String = "Today's reflection will arrive soon",
    minHeight: Dp = 160.dp,
    iconSize: Dp = 64.dp,
    inkColor: Color = Ink,
    mutedColor: Color = Ink60
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight),
        inkColor = inkColor,
        contentPadding = spacing.xLarge
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ClockGlyph(modifier = Modifier.size(iconSize), strokeColor = inkColor)

            Spacer(Modifier.height(spacing.medium))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = inkColor,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(spacing.xxSmall))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = mutedColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

