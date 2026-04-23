package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.automirrored.outlined.ShowChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.composables.BottomNavItem
import com.beslimir.cozy_stuff.composables.ParchmentBottomNav
import com.beslimir.cozy_stuff.theme.ParchmentTheme

private val previewItems = listOf(
    BottomNavItem(
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        contentDescription = "Home",
        label = "HOME",
    ),
    BottomNavItem(
        icon = Icons.Outlined.DateRange,
        selectedIcon = Icons.Filled.DateRange,
        contentDescription = "Seasons",
        label = "SEASONS",
    ),
    BottomNavItem(
        icon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        contentDescription = "Favorites",
        label = "FAVORITES",
    ),
    BottomNavItem(
        icon = Icons.AutoMirrored.Outlined.ShowChart,
        selectedIcon = Icons.AutoMirrored.Filled.ShowChart,
        contentDescription = "My Journey",
        label = "MY JOURNEY",
    ),
    BottomNavItem(
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
        contentDescription = "Settings",
        label = "SETTINGS",
    ),
)

@Preview(showBackground = true)
@Composable
private fun ParchmentBottomNavSeasonsSelectedPreview() {
    ParchmentTheme {
        ParchmentBottomNav(
            items = previewItems,
            selectedIndex = 1,
            onItemSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentBottomNavFavoritesSelectedPreview() {
    ParchmentTheme {
        ParchmentBottomNav(
            items = previewItems,
            selectedIndex = 2,
            onItemSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentBottomNavIconsOnlyPreview() {
    ParchmentTheme {
        ParchmentBottomNav(
            items = previewItems,
            selectedIndex = 0,
            onItemSelected = {},
            showLabels = false
        )
    }
}

@Preview(showBackground = true, heightDp = 200)
@Composable
private fun ParchmentBottomNavInContextPreview() {
    ParchmentTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ParchmentBottomNav(
                items = previewItems,
                selectedIndex = 3,
                onItemSelected = {},
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
