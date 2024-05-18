package com.androidClass.musicPlayer.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * This file defines the color palette for the Material Design (md) themes of the application in both light and dark modes.
 *
 * Each color is defined as a read-only (`val`) variable of the `Color` class in the Compose UI toolkit, with the color specified as an ARGB hex value.
 *
 * The color names follow a convention: `md_theme_{theme}_{colorRole}`.
 *
 * - `{theme}` can be `light` or `dark`, referring to the light and dark theme of the application respectively.
 *
 * - `{colorRole}` describes the role of the color in the Material Design specification.
 *   Examples include `primary`, `onPrimary`, `secondary`, `onSecondary`, `background`, `onBackground`, etc.
 *
 * The `on` color roles (such as `onPrimary`, `onSecondary`, etc.) are colors that guarantee legible text when drawn on top of the respective color.
 * For instance, `onPrimary` is a color that is clearly visible when drawn on top of the `primary` color.
 *
 * Some additional color roles like `surfaceVariant`, `outline`, `inverseOnSurface`, `inverseSurface`, `surfaceTint`, `outlineVariant`, and `scrim` are also defined,
 * which might be specific to the design needs of this application.
 */
val md_theme_light_primary = Color(0xFF00b59a)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF45FDDA)
val md_theme_light_onPrimaryContainer = Color(0xFF00201A)
val md_theme_light_secondary = Color(0xFF4B635C)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFCDE8DF)
val md_theme_light_onSecondaryContainer = Color(0xFF06201A)
val md_theme_light_tertiary = Color(0xFF436278)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFC8E7FF)
val md_theme_light_onTertiaryContainer = Color(0xFF001E2E)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFAFDFA)
val md_theme_light_onBackground = Color(0xFF191C1B)
val md_theme_light_surface = Color(0xFFFAFDFA)
val md_theme_light_onSurface = Color(0xFF191C1B)
val md_theme_light_surfaceVariant = Color(0xFFe5fff6)
val md_theme_light_onSurfaceVariant = Color(0xFF3F4946)
val md_theme_light_outline = Color(0xFF6F7975)
val md_theme_light_inverseOnSurface = Color(0xFFEFF1EF)
val md_theme_light_inverseSurface = Color(0xFF2E3130)
val md_theme_light_inversePrimary = Color(0xFF00DFBF)
val md_theme_light_surfaceTint = Color(0xFF006B5A)
val md_theme_light_outlineVariant = Color(0xFFBFC9C4)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF00DFBF)
val md_theme_dark_onPrimary = Color(0xFF00382E)
val md_theme_dark_primaryContainer = Color(0xFF005144)
val md_theme_dark_onPrimaryContainer = Color(0xFF45FDDA)
val md_theme_dark_secondary = Color(0xFFB1CCC4)
val md_theme_dark_onSecondary = Color(0xFF1D352F)
val md_theme_dark_secondaryContainer = Color(0xFF334B45)
val md_theme_dark_onSecondaryContainer = Color(0xFFCDE8DF)
val md_theme_dark_tertiary = Color(0xFFAACBE4)
val md_theme_dark_onTertiary = Color(0xFF113447)
val md_theme_dark_tertiaryContainer = Color(0xFF2A4A5F)
val md_theme_dark_onTertiaryContainer = Color(0xFFC8E7FF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF191C1B)
val md_theme_dark_onBackground = Color(0xFFE0E3E0)
val md_theme_dark_surface = Color(0xFF191C1B)
val md_theme_dark_onSurface = Color(0xFFE0E3E0)
val md_theme_dark_surfaceVariant = Color(0xFF3F4946)
val md_theme_dark_onSurfaceVariant = Color(0xFFBFC9C4)
val md_theme_dark_outline = Color(0xFF89938F)
val md_theme_dark_inverseOnSurface = Color(0xFF191C1B)
val md_theme_dark_inverseSurface = Color(0xFFE0E3E0)
val md_theme_dark_inversePrimary = Color(0xFF006B5A)
val md_theme_dark_surfaceTint = Color(0xFF00DFBF)
val md_theme_dark_outlineVariant = Color(0xFF3F4946)
val md_theme_dark_scrim = Color(0xFF000000)