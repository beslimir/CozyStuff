package com.beslimir.cozy_stuff.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import com.beslimir.cozy_stuff.theme.Ink
import com.beslimir.cozy_stuff.theme.Linen
import kotlin.math.roundToInt

/**
 * Screen layout that collapses a rich header into a compact top bar as the user scrolls;
 * headerContent is the expanded region, content is the scrollable body.
 */

@Composable
fun ParchmentCollapsingScreen(
    title: String,
    headerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Linen,
    textColor: Color = Ink,
    dividerColor: Color = Ink,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current

    var collapsedHeightPx by remember { mutableFloatStateOf(0f) }
    var expandedContentHeightPx by remember { mutableFloatStateOf(0f) }
    var scrollOffset by remember { mutableFloatStateOf(0f) }

    val collapseProgress =
        if (expandedContentHeightPx > 0f) (scrollOffset / expandedContentHeightPx).coerceIn(0f, 1f)
        else 0f

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < 0f) {
                    val old = scrollOffset
                    scrollOffset = (scrollOffset - available.y).coerceIn(0f, expandedContentHeightPx)
                    return Offset(0f, old - scrollOffset)
                }
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                if (available.y > 0f) {
                    val old = scrollOffset
                    scrollOffset = (scrollOffset - available.y).coerceIn(0f, expandedContentHeightPx)
                    return Offset(0f, old - scrollOffset)
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        val topPaddingDp = with(density) {
            (collapsedHeightPx + expandedContentHeightPx * (1f - collapseProgress)).toDp()
        }

        Box(modifier = Modifier.fillMaxSize().padding(top = topPaddingDp)) {
            content()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            ParchmentTopBar(
                title = title,
                onBack = onBack,
                actions = actions,
                backgroundColor = backgroundColor,
                textColor = textColor,
                dividerColor = if (collapseProgress >= 1f) dividerColor else Color.Transparent,
                modifier = Modifier.onGloballyPositioned {
                    val h = it.size.height.toFloat()
                    if (h != collapsedHeightPx) collapsedHeightPx = h
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 1f - collapseProgress }
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(
                            constraints.copy(minHeight = 0, maxHeight = Constraints.Infinity)
                        )
                        val intrinsic = placeable.height.toFloat()
                        if (intrinsic != expandedContentHeightPx) expandedContentHeightPx = intrinsic
                        val target = (intrinsic * (1f - collapseProgress)).roundToInt()
                        layout(placeable.width, target) { placeable.place(0, 0) }
                    }
            ) {
                headerContent()
            }

            if (collapseProgress < 1f) {
                ParchmentDivider(color = dividerColor)
            }
        }
    }
}
