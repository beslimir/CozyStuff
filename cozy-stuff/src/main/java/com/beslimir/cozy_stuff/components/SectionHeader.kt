package com.beslimir.cozy_stuff.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.theme.ParchmentTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

@Composable
fun SectionHeader(
    title: String,
    period: String,            // e.g., "Apr 5 – Jun 24 · White"
    description: String,       // short season description
    current: Int,
    total: Int,
    modifier: Modifier = Modifier,
    bgColor: Color = Linen,
    borderColor: Color = Ink,
    textColor: Color = Ink
) {
    val spacing = LocalSpacing.current

    ParchmentSurface(
        modifier = modifier.fillMaxWidth(),
        contentPadding = spacing.large,
        bgColor = bgColor,
        inkColor = borderColor
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
            Spacer(modifier = Modifier.height(spacing.xxSmall))
            Text(
                text = period,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
            Spacer(modifier = Modifier.height(spacing.small))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )
            Spacer(modifier = Modifier.height(spacing.medium))
            // Progress bar + label like "12 of 50 reflections received"
            ParchmentProgressLine(
                current = current,
                total = total,
                showLabel = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacing.small) // height token for visual balance
            )
            Spacer(modifier = Modifier.height(spacing.xxSmall))
            Text(
                text = "$current of $total reflections received",
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
        }
    }
}

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