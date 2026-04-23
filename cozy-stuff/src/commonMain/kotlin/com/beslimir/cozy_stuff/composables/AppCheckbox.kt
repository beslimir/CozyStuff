package com.beslimir.cozy_stuff.composables

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.Ink67
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.Olive40
import com.beslimir.cozy_stuff.theme.White

/**
 * Themed Material3 checkbox with Olive styling; use wherever a boolean item-level toggle is needed.
 */

@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedColor: Color = Olive,
    uncheckedColor: Color = Olive,
    checkmarkColor: Color = White,
    disabledColor: Color = Olive40,
    disabledContentColor: Color = Ink67
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        modifier = modifier,
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor,
            checkmarkColor = checkmarkColor,
            disabledCheckedColor = disabledColor,
            disabledUncheckedColor = disabledColor,
            disabledIndeterminateColor = disabledContentColor
        )
    )
}
