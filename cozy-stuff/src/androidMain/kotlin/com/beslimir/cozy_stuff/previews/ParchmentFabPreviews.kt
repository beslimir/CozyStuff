package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.ParchmentFab
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentFabAddPreview() {
    ParchmentTheme {
        ParchmentFab(
            icon = Icons.Outlined.Add,
            contentDescription = "Add reflection",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentFabEditPreview() {
    ParchmentTheme {
        ParchmentFab(
            icon = Icons.Outlined.Edit,
            contentDescription = "Edit",
            onClick = {},
            containerColor = Olive,
            iconTintColor = Linen,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentFabBothPreview() {
    ParchmentTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ParchmentFab(
                icon = Icons.Outlined.Add,
                contentDescription = "Add",
                onClick = {}
            )
            ParchmentFab(
                icon = Icons.Outlined.Edit,
                contentDescription = "Edit",
                onClick = {},
                containerColor = Olive,
                iconTintColor = Linen
            )
        }
    }
}
