package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Modal dialog with a ParchmentSurface container, a title, body text,
 * and Secondary/Primary action buttons.
 */

@Composable
fun ParchmentDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    bgColor: Color = Linen,
    borderColor: Color = Ink,
    textColor: Color = Ink
) {
    val spacing = LocalSpacing.current

    Dialog(onDismissRequest = onDismiss) {
        ParchmentSurface(
            modifier = modifier,
            bgColor = bgColor,
            inkColor = borderColor
        ) {
            Column(
                modifier = Modifier.padding(spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = textColor,
                )

                Spacer(modifier = Modifier.height(spacing.small))

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor,
                )

                Spacer(modifier = Modifier.height(spacing.medium))

                Row(horizontalArrangement = Arrangement.spacedBy(spacing.small)) {
                    SecondaryButton(
                        text = dismissText,
                        onClick = onDismiss,
                    )
                    PrimaryButton(
                        text = confirmText,
                        onClick = onConfirm,
                    )
                }
            }
        }
    }
}
