package com.beslimir.cozy_stuff.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ParchmentTopBar
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentTopBarWithBackPreview() {
    ParchmentTheme {
        ParchmentTopBar(
            title = "Spring Season",
            onBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTopBarNoBackPreview() {
    ParchmentTheme {
        ParchmentTopBar(title = "My Reflections")
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTopBarWithActionsPreview() {
    ParchmentTheme {
        ParchmentTopBar(
            title = "Bookmarks",
            onBack = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "More options",
                        tint = Ink
                    )
                }
            }
        )
    }
}
