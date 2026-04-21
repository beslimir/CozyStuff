package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.AppCheckbox
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun AppCheckboxCheckedPreview() {
    ParchmentTheme {
        AppCheckbox(checked = true, onCheckedChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppCheckboxUncheckedPreview() {
    ParchmentTheme {
        AppCheckbox(checked = false, onCheckedChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppCheckboxDisabledPreview() {
    ParchmentTheme {
        AppCheckbox(checked = true, onCheckedChange = {}, enabled = false)
    }
}
