package cz.ackee.testtask.rm.character.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.core.domain.app.FilledWidthByScreenType
import cz.ackee.testtask.rm.core.presentation.modifier.bounceClickEffect
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.theme.CurrWindowType

@Composable
fun CharacterComponentFilled(
    state: CharacterUiState,
    onClick: (id: Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .bounceClickEffect { onClick(state.id) }
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth(FilledWidthByScreenType(.96f).get(CurrWindowType))
            .background(AppColors.surface)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        CharacterComponentContent(state = state)
    }
}