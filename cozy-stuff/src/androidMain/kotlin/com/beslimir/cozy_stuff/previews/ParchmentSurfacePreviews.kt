package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ParchmentSurface
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentSurfacePreview() {
    ParchmentTheme {
        ParchmentSurface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "What you seek, you will find.",
                color = Ink,
            )
        }
    }
}
