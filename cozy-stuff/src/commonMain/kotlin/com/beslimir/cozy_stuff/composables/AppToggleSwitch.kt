package com.beslimir.cozy_stuff.composables

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.Olive35
import com.beslimir.cozy_stuff.theme.White

/**
 * Themed Material3 toggle switch with Olive track; use for on/off settings and preference toggles.
 */

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
