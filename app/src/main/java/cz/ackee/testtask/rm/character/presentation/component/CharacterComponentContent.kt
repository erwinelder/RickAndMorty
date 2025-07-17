package cz.ackee.testtask.rm.character.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.core.presentation.theme.AppColors

@Composable
fun RowScope.CharacterComponentContent(
    state: CharacterUiState
) {
    // image
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.name,
                color = AppColors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
            if (state.isFavourite) {
                Icon(
                    painter = painterResource(R.drawable.favorites),
                    contentDescription = "favorites",
                    tint = AppColors.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = state.status,
            color = AppColors.outline,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}