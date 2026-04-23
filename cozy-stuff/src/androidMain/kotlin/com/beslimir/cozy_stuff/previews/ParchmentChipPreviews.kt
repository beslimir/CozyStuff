package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.ParchmentChip
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentChipUnselectedPreview() {
    ParchmentTheme {
        ParchmentChip(
            label = "SPRING",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentChipSelectedPreview() {
    ParchmentTheme {
        ParchmentChip(
            label = "SPRING",
            isSelected = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun ParchmentChipGroupPreview() {
    ParchmentTheme {
        FlowRow(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ParchmentChip(label = "ALL", isSelected = true, onClick = {})
            ParchmentChip(label = "SPRING", onClick = {})
            ParchmentChip(label = "SUMMER", onClick = {})
            ParchmentChip(label = "AUTUMN", onClick = {})
            ParchmentChip(label = "WINTER", onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentChipStaticPreview() {
    ParchmentTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ParchmentChip(label = "ACTIVE")
            ParchmentChip(label = "COMPLETED", isSelected = true)
        }
    }
}
