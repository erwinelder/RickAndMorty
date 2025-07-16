package cz.ackee.testtask.rm.core.presentation.navigation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.ackee.testtask.rm.core.presentation.navigation.model.NavButtonState
import cz.ackee.testtask.rm.core.presentation.theme.AppColors

@Composable
fun BottomNavButtonComponent(
    state: NavButtonState
) {
    val color by animateColorAsState(
        targetValue = if (state.isActive) AppColors.primary else AppColors.disabled
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(state.iconRes),
            contentDescription = stringResource(state.nameRes),
            tint = color,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = stringResource(state.nameRes),
            color = color,
            fontSize = 16.sp
        )
    }
}