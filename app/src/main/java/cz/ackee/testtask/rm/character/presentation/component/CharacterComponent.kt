package cz.ackee.testtask.rm.character.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.core.domain.app.FilledWidthByScreenType
import cz.ackee.testtask.rm.core.presentation.modifier.bounceClickEffect
import cz.ackee.testtask.rm.core.presentation.theme.CurrWindowType

@Composable
fun CharacterComponent(
    state: CharacterUiState,
    onClick: (id: Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .bounceClickEffect { onClick(state.id) }
            .fillMaxWidth(FilledWidthByScreenType(.9f).get(CurrWindowType))
    ) {
        CharacterComponentContent(state = state)
    }
}