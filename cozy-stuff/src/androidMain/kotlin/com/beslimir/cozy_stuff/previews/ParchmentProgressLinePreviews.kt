package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ParchmentProgressLine
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Preview(showBackground = true)
@Composable
private fun ParchmentProgressLineHalfPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        ParchmentProgressLine(
            current = 12,
            total = 50,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.large)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentProgressLineFullPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        ParchmentProgressLine(
            current = 50,
            total = 50,
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.large)
        )
    }
}
