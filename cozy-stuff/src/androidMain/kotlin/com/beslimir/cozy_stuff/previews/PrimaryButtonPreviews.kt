package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.PrimaryButton
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    ParchmentTheme {
        PrimaryButton(text = "Confirm", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonDisabledPreview() {
    ParchmentTheme {
        PrimaryButton(text = "Confirm", onClick = {}, isDisabled = true)
    }
}
