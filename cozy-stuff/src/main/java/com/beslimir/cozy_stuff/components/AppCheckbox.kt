package com.beslimir.cozy_stuff.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.theme.Ink67
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.Olive40
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.theme.White

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