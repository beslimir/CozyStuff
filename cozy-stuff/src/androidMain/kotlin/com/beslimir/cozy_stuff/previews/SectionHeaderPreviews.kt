package com.beslimir.cozy_stuff.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.components.SectionHeader
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    ParchmentTheme {
        SectionHeader(
            title = "Spring Season",
            period = "Apr 5 – Jun 24 · Green",
            description = "Fifty days of renewal and natural growth.",
            current = 12,
            total = 50
        )
    }
}
