package com.beslimir.cozy_stuff.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Composable
fun ConfirmationDialog(
    title: String = "Delete item?",
    message: String = "This action cannot be undone.",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Delete",
    dismissText: String = "Cancel",
    bgColor: Color = Linen,
    borderColor: Color = Ink,
    textColor: Color = Ink
) {
    ParchmentDialog(
        title = title,
        message = message,
        confirmText = confirmText,
        dismissText = dismissText,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        modifier = modifier,
        bgColor = bgColor,
        borderColor = borderColor,
        textColor = textColor
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmationDialogPreview() {
    ParchmentTheme {
        ConfirmationDialog(
            title = "Delete item?",
            message = "This action cannot be undone.",
            onConfirm = {},
            onDismiss = {}
        )
    }
}