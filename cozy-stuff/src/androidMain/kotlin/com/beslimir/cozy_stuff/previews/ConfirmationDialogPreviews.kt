package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ConfirmationDialog
import com.beslimir.cozy_stuff.theme.ParchmentTheme

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
