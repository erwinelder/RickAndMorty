package cz.ackee.testtask.rm.core.presentation.component.text

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.theme.AppTypography

@Composable
fun MessageComponent(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = AppColors.outline,
            style = AppTypography.normal
        )
    }
}