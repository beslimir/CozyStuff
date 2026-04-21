package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.SecondaryButton
import com.beslimir.cozy_stuff.theme.ParchmentTheme

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
