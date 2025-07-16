package cz.ackee.testtask.rm.core.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class AppCustomTypography(
    val title : TextStyle = TextStyle(
        textAlign = TextAlign.Start,
        fontSize = 24.sp,
        fontWeight = FontWeight.W600
    ),
    val normal: TextStyle = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
)