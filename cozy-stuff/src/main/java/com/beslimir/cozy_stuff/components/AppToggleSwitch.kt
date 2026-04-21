package com.beslimir.cozy_stuff.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.Olive35
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.theme.White

@Composable
fun AppToggleSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedThumbColor: Color = White,
    uncheckedThumbColor: Color = White,
    checkedTrackColor: Color = Olive,
    uncheckedTrackColor: Color = Olive35
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = checkedThumbColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedThumbColor = uncheckedThumbColor,
            uncheckedTrackColor = uncheckedTrackColor
        )
    )
}

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