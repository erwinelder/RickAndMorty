package cz.ackee.testtask.rm.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class AppPalette(

) {
    data object Light : AppPalette()
    data object Dark : AppPalette()
}