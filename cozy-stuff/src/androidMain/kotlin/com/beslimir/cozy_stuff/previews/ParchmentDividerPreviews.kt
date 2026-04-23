package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ParchmentDivider
import com.beslimir.cozy_stuff.theme.Clay
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Preview(showBackground = true)
@Composable
private fun ParchmentDividerPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        Column(modifier = Modifier.padding(spacing.large)) {
            Text("SECTION ONE", style = MaterialTheme.typography.labelMedium)
            ParchmentDivider(modifier = Modifier.padding(vertical = spacing.medium))
            Text("SECTION TWO", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentDividerClayPreview() {
    ParchmentTheme {
        val spacing = LocalSpacing.current
        Column(modifier = Modifier.padding(spacing.large)) {
            Text("WARM ACCENT DIVIDER", style = MaterialTheme.typography.labelMedium)
            ParchmentDivider(
                color = Clay,
                modifier = Modifier.padding(vertical = spacing.medium)
            )
            Text("CONTENT BELOW", style = MaterialTheme.typography.labelMedium)
        }
    }
}
