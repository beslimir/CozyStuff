package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.tokens.LocalSpacing

/**
 * Simple full-bleed top app bar with a title, an optional back arrow, and a trailing actions slot;
 * respects status-bar insets.
 */

@Composable
fun ParchmentTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Linen,
    textColor: Color = Ink,
    dividerColor: Color = Ink,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.medium, vertical = spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier.width(spacing.xxSmall))
            } else {
                Spacer(modifier = Modifier.width(spacing.medium))
            }

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = textColor,
                modifier = Modifier.weight(1f)
            )

            Row(content = actions)
        }

        ParchmentDivider(color = dividerColor)
    }
}
