package cz.ackee.testtask.rm.core.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class AppPalette(
    val primary: Color,
    val disabled: Color,
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val outline: Color,
) {
    data object Light : AppPalette(
        primary = Color(0, 0, 255),
        disabled = Color(179, 179, 179),
        background = Color(244, 244, 249),
        surface = Color(255, 255, 255),
        onSurface = Color(0, 0, 0),
        outline = Color(102, 102, 102),
    )
    data object Dark : AppPalette(
        primary = Color(149, 149, 254),
        disabled = Color(93, 93, 94),
        background = Color(24, 24, 25),
        surface = Color(46, 46, 46),
        onSurface = Color(255, 255, 255),
        outline = Color(139, 139, 140),
    )
}