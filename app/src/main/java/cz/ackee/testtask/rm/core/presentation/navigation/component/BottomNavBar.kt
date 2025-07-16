package cz.ackee.testtask.rm.core.presentation.navigation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.core.presentation.navigation.model.NavButtonState
import cz.ackee.testtask.rm.core.presentation.theme.AppColors

@Composable
fun BottomNavBar(
    navButtons: List<NavButtonState>,
    isVisible: Boolean = true
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .shadow(
                    elevation = 16.dp
                )
                .background(AppColors.background)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            navButtons.forEach { button ->
                BottomNavButtonComponent(state = button)
            }
        }
    }
}