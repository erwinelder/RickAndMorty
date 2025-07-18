package cz.ackee.testtask.rm.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharactersUseCase
import cz.ackee.testtask.rm.character.mapper.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class FavouriteCharactersViewModel(
    getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val characters = getFavoriteCharactersUseCase.execute()
        .mapLatest { result ->
            result.getDataIfSuccess()?.map { it.toUiState(isFavourite = true) } ?: emptyList()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}