package cz.ackee.testtask.rm.core.presentation.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.core.presentation.theme.AppColors

@Composable
fun TopBarContainer(
    padding: PaddingValues = PaddingValues(),
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(
                elevation = 8.dp
            )
            .background(AppColors.background)
            .fillMaxWidth()
            .padding(padding),
        content = content
    )
}