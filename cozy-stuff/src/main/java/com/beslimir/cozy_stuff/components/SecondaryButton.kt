package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.beslimir.cozy_stuff.extensions.SketchStyle
import com.beslimir.cozy_stuff.extensions.sketchParchment
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink67
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.theme.Transparent
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    containerColor: Color = Linen,
    contentColor: Color = Ink,
    borderColor: Color = Ink,
    strokeWidth: Dp = LocalSpacing.current.xxxSmall
) {
    val shape = AppShapes.small
    val spacing = LocalSpacing.current

    Box(
        modifier = modifier
            .clip(shape)
            .sketchParchment(
                bgColor = containerColor,
                inkColor = borderColor,
                style = SketchStyle(cornerRadius = spacing.small, strokeWidth = strokeWidth)
            )
    ) {
        Button(
            onClick = onClick,
            enabled = !isDisabled,
            interactionSource = remember { MutableInteractionSource() },
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Transparent,
                contentColor = contentColor,
                disabledContainerColor = Transparent,
                disabledContentColor = Ink67
            ),
            elevation = ButtonDefaults.buttonElevation(
                spacing.zero,
                spacing.zero,
                spacing.zero,
                spacing.zero,
                spacing.zero,
            ),
            modifier = Modifier
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = spacing.small,
                    vertical = spacing.xxSmall,
                ),
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondaryButtonPreview() {
    ParchmentTheme {
        SecondaryButton(text = "Cancel", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SecondaryButtonDisabledPreview() {
    ParchmentTheme {
        SecondaryButton(text = "Cancel", onClick = {}, isDisabled = true)
    }
}