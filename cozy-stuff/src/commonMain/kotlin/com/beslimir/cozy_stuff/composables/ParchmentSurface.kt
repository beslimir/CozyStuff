package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.extensions.SketchStyle
import com.beslimir.cozy_stuff.extensions.sketchParchment
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun ParchmentSurface(
    modifier: Modifier = Modifier,
    shape: Shape = AppShapes.small,
    style: SketchStyle = SketchStyle(),
    contentPadding: Dp? = null,
    bgColor: Color = Linen,
    inkColor: Color = Ink,
    content: @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    val padding = contentPadding ?: spacing.medium

    Box(
        modifier = modifier
            .clip(shape)
            .sketchParchment(
                bgColor = bgColor,
                inkColor = inkColor,
                style = style,
            )
            .padding(padding)
    ) {
        content()
    }
}
