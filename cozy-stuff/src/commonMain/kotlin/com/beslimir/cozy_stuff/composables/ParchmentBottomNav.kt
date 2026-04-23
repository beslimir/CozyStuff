package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Ink60
import com.beslimir.cozy_stuff.theme.Linen
import com.beslimir.cozy_stuff.theme.Olive35
import com.beslimir.cozy_stuff.theme.AppShapes
import com.beslimir.cozy_stuff.tokens.LocalSpacing

data class BottomNavItem(
    val icon: ImageVector,
    val contentDescription: String,
    val label: String? = null,
    val selectedIcon: ImageVector? = null,
)

/**
 * Full-width bottom navigation bar with icon-and-label items and a pill highlight on the
 * selected tab; supports up to 5 destinations.
 */

@Composable
fun ParchmentBottomNav(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    showLabels: Boolean = true,
    backgroundColor: Color = Linen,
    selectedIndicatorColor: Color = Olive35,
    selectedContentColor: Color = Ink,
    unselectedContentColor: Color = Ink60,
    dividerColor: Color = Ink,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .navigationBarsPadding()
    ) {
        ParchmentDivider(color = dividerColor)

        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index, item ->
                BottomNavItemContent(
                    item = item,
                    isSelected = index == selectedIndex,
                    showLabel = showLabels,
                    selectedIndicatorColor = selectedIndicatorColor,
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor,
                    onClick = { onItemSelected(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BottomNavItemContent(
    item: BottomNavItem,
    isSelected: Boolean,
    showLabel: Boolean,
    selectedIndicatorColor: Color,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val contentColor = if (isSelected) selectedContentColor else unselectedContentColor
    val icon = if (isSelected && item.selectedIcon != null) item.selectedIcon else item.icon

    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(vertical = spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(AppShapes.extraLarge)
                .background(if (isSelected) selectedIndicatorColor else Color.Transparent)
                .padding(horizontal = spacing.medium, vertical = spacing.xSmall),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = item.contentDescription,
                tint = contentColor
            )
        }

        if (showLabel && item.label != null) {
            Spacer(modifier = Modifier.height(spacing.xxSmall))
            Text(
                text = item.label,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
