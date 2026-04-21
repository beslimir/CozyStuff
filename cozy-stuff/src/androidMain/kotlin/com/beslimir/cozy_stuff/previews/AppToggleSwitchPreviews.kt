package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.AppToggleSwitch
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun AppToggleSwitchOnPreview() {
    ParchmentTheme {
        AppToggleSwitch(checked = true, onCheckedChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppToggleSwitchOffPreview() {
    ParchmentTheme {
        AppToggleSwitch(checked = false, onCheckedChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppToggleSwitchDisabledPreview() {
    ParchmentTheme {
        AppToggleSwitch(checked = true, onCheckedChange = {}, enabled = false)
    }
}
