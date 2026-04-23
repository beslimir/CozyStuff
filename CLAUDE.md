# CozyStuff — Design System Reference

KMP Compose library (Android + iOS). All sources in `cozy-stuff/src/commonMain/`. Version 2.0.0 on JitPack.

## Visual Identity

**"Cozy Parchment"** — warm, contemplative, hand-crafted. Think journal pages and natural materials. No bright primaries, no sharp corners, no deep shadows. Everything feels hand-drawn and unhurried.

Signature trait: **jittered borders**. Outlines are never perfectly straight — they wobble slightly (`wobblePx = 1.8f`) to feel drawn, not rendered. This applies to all cards, buttons, and containers.

---

## Primitives

### ParchmentSurface — the default container
```kotlin
ParchmentSurface(
    modifier = modifier.fillMaxWidth(),
    inkColor = inkColor,       // border color, default Ink
    bgColor = Linen,           // background, default Linen
    contentPadding = spacing.large
) { /* content */ }
```
Use this for **every new card, panel, or container**. It handles clip, background, sketchy jittered border, and soft shadow automatically via the `sketchParchment` modifier.

**Never** replicate its behavior manually with `.clip` + `.background` + `.drawBehind`. Always compose with `ParchmentSurface`.

### ParchmentCard
Material3 `Card` with a clean 2dp ink border and 2dp elevation. Use when many cards stack in a list or grid and a sketchy border would feel cluttered.

### ScreenHeaderFullWidth
Full-bleed header (no card wrapper). Has a wobbly divider line drawn at the bottom. Use at the top of navigation stacks only.

---

## Color Palette
`com.beslimir.cozy_stuff.theme.Color`

| Token | Hex | Role |
|---|---|---|
| `Linen` | `#F6F2EF` | Default background and card fill |
| `Ink` | `#3A3A2E` | Text, borders, icons |
| `Olive` | `#8E9367` | Primary interactive (buttons, checkmarks, accents) |
| `Clay` | `#CAA45A` | Secondary / warm accent |
| `ClayLight` | `#D9C297` | Lighter warm tan |
| `DisabledSurface` | `#EAE4DC` | Disabled backgrounds |

**Alpha variants** — use these instead of `.copy(alpha = ...)`:
`Linen90`, `Olive35`, `Olive40`, `ClayLight45`, `Ink18`, `Ink45`, `Ink60`, `Ink67`, `Ink80`, `Black06`

Season colors for `SegmentedWheel`: `SeasonPurple`, `SeasonGold`, `SeasonGreen`, `SeasonViolet`, `SeasonCream`.

---

## Spacing Tokens
`com.beslimir.cozy_stuff.tokens.LocalSpacing`

Always access via `val spacing = LocalSpacing.current`. Never hardcode dp values.

| Token | Value | Typical use |
|---|---|---|
| `xxxSmall` | 2dp | Border/stroke width |
| `xxSmall` | 4dp | Minimal gaps |
| `xSmall` | 6dp | Tight padding |
| `small` | 8dp | Internal gaps |
| `medium` | 12dp | Standard padding (ParchmentSurface default) |
| `large` | 16dp | Section content padding |
| `xLarge` | 24dp | Generous content padding (EmptyMessageCard) |
| `xxLarge` | 32dp | Large components |
| `xxxLarge` | 40dp | Icon containers |

---

## Shapes
`com.beslimir.cozy_stuff.theme.AppShapes`

| Token | Radius | Default for |
|---|---|---|
| `extraSmall` | 6dp | — |
| `small` | 8dp | **Most composables** (ParchmentSurface default) |
| `medium` | 12dp | — |
| `large` | 16dp | — |
| `extraLarge` | 24dp | — |

---

## Typography
`com.beslimir.cozy_stuff.theme.AppTypography` — system serif, warm and readable.

| Style | Size | Weight | Use |
|---|---|---|---|
| `displaySmall` | 28sp / 34sp lh | SemiBold | Screen titles |
| `headlineSmall` | 22sp / 28sp lh | SemiBold | Card titles |
| `titleMedium` | 18sp / 24sp lh | Medium | List item titles |
| `bodyLarge` | 16sp / 22sp lh | Normal | Main content |
| `bodyMedium` | 14sp / 20sp lh | Normal | Secondary text |
| `labelLarge` | 14sp | SemiBold | — |
| `labelMedium` | 12sp | Medium | Captions |

---

## Sketch Aesthetic — SketchStyle
`com.beslimir.cozy_stuff.extensions.SketchStyle`

Default values — rarely need to override:
- `strokeWidth = 1.5dp` — thin, delicate
- `wobblePx = 1.8f` — subtle jitter
- `cornerRadius = 8dp` — matches `AppShapes.small`
- `shadowAlpha = 0.08f` — very soft
- `noiseAlpha = 0.06f` — optional paper texture
- `seed = 1337` — reproducible jitter

The `sketchParchment` modifier draws: soft shadow → solid background → jittered border → optional noise. It is applied internally by `ParchmentSurface`.

---

## Composable Conventions

### Parameter order
```kotlin
@Composable
fun MyComponent(
    // 1. Required data
    title: String,
    // 2. Required callbacks
    onClick: () -> Unit,
    // 3. Modifier (always optional with empty default)
    modifier: Modifier = Modifier,
    // 4. Layout/size customization
    minHeight: Dp = 160.dp,
    iconSize: Dp = LocalSpacing.current.xxxLarge,
    // 5. Colors — always defaulting to theme tokens
    inkColor: Color = Ink,
    mutedColor: Color = Ink60,
    containerColor: Color = Linen,
    // 6. Trailing content lambda (if any)
    content: @Composable () -> Unit = {}
)
```

### Color parameter naming
| Purpose | Parameter name |
|---|---|
| Primary text / borders | `inkColor` or `textColor` |
| Secondary / muted text | `mutedColor` |
| Card / container fill | `bgColor` or `containerColor` |
| Icon container fill | `iconBackgroundColor` |
| Icon tint | `iconTintColor` |

### Boolean flags: `is` prefix
`isDisabled`, `isCurrent`, `isBookmarked`, `isAvailable`

### Callbacks: action verb
`onClick`, `onToggle`, `onBookmarkToggle`, `onConfirm`, `onDismiss`, `onBack`

---

## Existing Composables

| Composable | Purpose | Container |
|---|---|---|
| `ParchmentSurface` | Base sketchy container | self |
| `ParchmentCard` | Material3 card variant | Material Card |
| `ParchmentDialog` | Dialog with title/body/buttons | ParchmentSurface |
| `ConfirmationDialog` | Delete confirmation preset | ParchmentDialog |
| `ParchmentDivider` | Wobbly ink horizontal divider line | Canvas |
| `ParchmentProgressLine` | Horizontal progress bar | Canvas |
| `ParchmentTextField` | Text input with optional label and placeholder | ParchmentSurface |
| `ParchmentTopBar` | Simple full-bleed screen header: title + optional back + optional actions | no container |
| `ParchmentChip` | Pill-shaped tag / filter chip, selectable | sketchParchment |
| `ParchmentFab` | Floating action button (icon + sketchy border) | ParchmentSurface |
| `ParchmentBottomNav` | Bottom navigation bar (up to 5 tabs, pill indicator on selected) | no container |
| `ParchmentCollapsingScreen` | Screen layout with collapsing header: rich content collapses to a top bar on scroll | no container |
| `ParchmentTimePicker` | Time picker: `– H +  :  – M +` buttons; `minuteStep: MinuteInterval` controls step size (1 / 5 / 15 min); optional AM/PM chips | ParchmentSurface |
| `PrimaryButton` | Main action (Clay bg) | sketchParchment |
| `SecondaryButton` | Secondary action (Linen bg) | sketchParchment |
| `AppCheckbox` | Checkbox (Olive check) | Material Checkbox |
| `AppToggleSwitch` | Switch (Olive track) | Material Switch |
| `FavoriteToggle` | Heart icon button | IconButton |
| `MessageCard` | Message/reflection card | ParchmentSurface |
| `ListItemCard` | Generic list item | ParchmentSurface |
| `MessageListItem` | Indexed list item | ParchmentSurface |
| `EmptyMessageCard` | In-list placeholder card with clock | ParchmentSurface |
| `EmptyScreen` | Full-screen empty state with leaf icon | no container |
| `SectionHeader` | Section title + progress | ParchmentSurface |
| `ScreenHeaderFullWidth` | Full-bleed screen header | no container |
| `SegmentedWheel` | Radial season selector | Canvas |

**Reusable glyphs** (in `components/`, Canvas-drawn, stroke-only, no fill):

| Glyph | File | Theme |
|---|---|---|
| `ClockGlyph` | `ClockGlyph.kt` | Time / "not yet" |
| `LeafGlyph` | `LeafGlyph.kt` | Growth / new beginnings |
| `CrossGlyph` | `CrossGlyph.kt` | Faith |
| `StarGlyph` | `StarGlyph.kt` | 5-pointed star / aspiration |
| `HeartGlyph` | `HeartGlyph.kt` | Love / devotion |
| `FlameGlyph` | `FlameGlyph.kt` | Spirit / warmth (outer + inner flame) |
| `AnchorGlyph` | `AnchorGlyph.kt` | Hope / steadfastness |
| `DoveGlyph` | `DoveGlyph.kt` | Peace / Holy Spirit |
| `OliveBranchGlyph` | `OliveBranchGlyph.kt` | Peace / blessing (diagonal stem + 3 leaf pairs) |
| `BookGlyph` | `BookGlyph.kt` | Open book with text lines |
| `BibleGlyph` | `BibleGlyph.kt` | Closed book with spine + cross on cover |
| `PrayingHandsGlyph` | `PrayingHandsGlyph.kt` | Prayer / supplication |

All glyphs take `modifier` and `strokeColor` (default `Ink`). Previews are in `GlyphPreviews.kt`.

---

## Previews

**Previews never live in `commonMain`.** They belong in `androidMain`, in a dedicated file per composable:
```
cozy-stuff/src/androidMain/kotlin/com/beslimir/cozy_stuff/previews/<Name>Previews.kt
```

Pattern to follow — always wrap with `ParchmentTheme`, use `@Preview(showBackground = true)`:
```kotlin
package com.beslimir.cozy_stuff.previews

import androidx.compose.ui.tooling.preview.Preview
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun MyComponentPreview() {
    ParchmentTheme {
        MyComponent(...)
    }
}
```

- **Never** use `Surface(color = Linen)` as the preview wrapper — use `ParchmentTheme`.
- Segment/label text in previews is UPPERCASE.
- Size the composable via `Modifier.size(...)` on the composable itself, not via the `@Preview` annotation dimensions.

---

## Rules for New Composables

1. **Always wrap with `ParchmentSurface`** — never re-implement clip + background + border manually. Exception: screen-level composables (`EmptyScreen`, `ScreenHeaderFullWidth`) are full-bleed and have no card wrapper.
2. **Never hardcode dp or color values** — use `LocalSpacing.current` and theme color tokens.
3. **Default colors to theme tokens** — `Ink`, `Ink60`, `Linen`, `Olive` as appropriate.
4. **Shape defaults to `AppShapes.small`** (8dp) unless there's a specific reason.
5. **No comments explaining what the code does** — only add a comment if the *why* is non-obvious (a constraint, invariant, or workaround).
6. **Match the warm, organic feel** — if something looks too geometric or digital, consider whether it fits the aesthetic.
7. **Previews go in `androidMain/previews/`** — never inside the composable file in `commonMain`.
