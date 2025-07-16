package cz.ackee.testtask.rm.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class AppPalette(
    private val primary: Color,
    private val disabled: Color,
    private val background: Color,
    private val surface: Color,
    private val onSurface: Color,
    private val outline: Color,
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
        surface = Color(0, 0, 0, 1),
        onSurface = Color(255, 255, 255),
        outline = Color(139, 139, 140),
    )
}