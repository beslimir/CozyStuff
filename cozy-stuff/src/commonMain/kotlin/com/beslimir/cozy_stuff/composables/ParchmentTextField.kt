package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Text input with an optional label above and placeholder inside, wrapped in a ParchmentSurface;
 * supports both single-line and multi-line modes.
 */

@Composable
fun ParchmentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    inkColor: Color = Ink,
    mutedColor: Color = Ink60,
    bgColor: Color = Linen,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val spacing = LocalSpacing.current

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = mutedColor
            )
            Spacer(modifier = Modifier.height(spacing.xxSmall))
        }

        ParchmentSurface(
            modifier = Modifier.fillMaxWidth(),
            bgColor = bgColor,
            inkColor = inkColor,
            contentPadding = spacing.medium
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = maxLines == 1,
                minLines = minLines,
                maxLines = maxLines,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = inkColor),
                cursorBrush = SolidColor(inkColor),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (value.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodyLarge,
                                color = mutedColor
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}
