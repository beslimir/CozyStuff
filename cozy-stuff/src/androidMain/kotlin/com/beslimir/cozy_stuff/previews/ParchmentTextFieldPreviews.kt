package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.ParchmentTextField
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentTextFieldEmptyPreview() {
    ParchmentTheme {
        ParchmentTextField(
            value = "",
            onValueChange = {},
            label = "REFLECTION",
            placeholder = "Write your thoughts here...",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTextFieldFilledPreview() {
    ParchmentTheme {
        ParchmentTextField(
            value = "Today I felt a deep sense of gratitude for the quiet morning.",
            onValueChange = {},
            label = "REFLECTION",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTextFieldMultilinePreview() {
    ParchmentTheme {
        ParchmentTextField(
            value = "Day one of the fast.\nThe silence felt heavy at first,\nbut then peaceful.",
            onValueChange = {},
            label = "JOURNAL ENTRY",
            placeholder = "Begin writing...",
            minLines = 5,
            maxLines = 10,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTextFieldNoLabelPreview() {
    ParchmentTheme {
        ParchmentTextField(
            value = "",
            onValueChange = {},
            placeholder = "Search...",
            modifier = Modifier.padding(16.dp)
        )
    }
}
