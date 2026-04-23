package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.components.ClockGlyph
import com.beslimir.cozy_stuff.components.LeafGlyph
import com.beslimir.cozy_stuff.theme.ParchmentTheme

private val glyphSize = Modifier.size(80.dp)

@Preview(showBackground = true)
@Composable
private fun LeafGlyphPreview() {
    ParchmentTheme { LeafGlyph(modifier = glyphSize) }
}

@Preview(showBackground = true)
@Composable
private fun ClockGlyphPreview() {
    ParchmentTheme { ClockGlyph(modifier = glyphSize) }
}
