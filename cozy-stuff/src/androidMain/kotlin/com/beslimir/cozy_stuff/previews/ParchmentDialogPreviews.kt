package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.ParchmentDialog
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentDialogPreview() {
    ParchmentTheme {
        ParchmentDialog(
            title = "Delete item?",
            message = "This action cannot be undone.",
            confirmText = "Delete",
            dismissText = "Cancel",
            onConfirm = {},
            onDismiss = {}
        )
    }
}
