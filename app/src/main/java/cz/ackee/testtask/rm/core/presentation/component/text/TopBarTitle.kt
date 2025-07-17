package cz.ackee.testtask.rm.core.presentation.component.text

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.theme.AppTypography

@Composable
fun RowScope.TopBarTitle(
    text: String
) {
    Text(
        text = text,
        color = AppColors.onSurface,
        style = AppTypography.title,
        modifier = Modifier.weight(1f)
    )
}