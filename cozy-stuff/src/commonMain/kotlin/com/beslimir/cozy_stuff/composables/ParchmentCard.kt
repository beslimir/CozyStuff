package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Material3 Card with a clean ink border and slight elevation; use when many cards stack in a
 * list or grid and a sketchy border would feel cluttered.
 */

@Composable
fun ParchmentCard(
    modifier: Modifier = Modifier,
    shape: Shape = AppShapes.small,
    containerColor: Color = Linen,
    contentColor: Color = Ink,
    borderColor: Color = Ink,
    borderWidth: Dp = LocalSpacing.current.xxxSmall,
    elevation: Dp = LocalSpacing.current.xxxSmall,
    content: @Composable () -> Unit
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .clip(shape)
            .border(BorderStroke(borderWidth, borderColor), shape),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = shape
    ) {
        Box(modifier = Modifier.padding(spacing.medium)) {
            content()
        }
    }
}
