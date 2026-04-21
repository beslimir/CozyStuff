package com.beslimir.cozy_stuff.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.ParchmentCard
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentCardPreview() {
    ParchmentTheme {
        ParchmentCard {
            Text(text = "Still waters run deep.", color = Ink)
        }
    }
}
