package cz.ackee.testtask.rm.core.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class AppCustomTypography(
    val normal: TextStyle = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
)