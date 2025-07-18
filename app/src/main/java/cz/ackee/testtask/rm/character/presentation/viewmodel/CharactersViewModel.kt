package cz.ackee.testtask.rm.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharacterIdsUseCase
import cz.ackee.testtask.rm.character.mapper.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModel(
    getFavoriteCharacterIdsUseCase: GetFavoriteCharacterIdsUseCase,
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _favouriteCharacterIds = MutableStateFlow<Set<Int>>(emptySet())

    val characters = getCharactersUseCase.execute()
        .cachedIn(scope = viewModelScope)
        .combine(_favouriteCharacterIds) { pagingData, favouriteIds ->
            pagingData.map { character ->
                character.toUiState(isFavourite = character.id in favouriteIds)
            }
        }

    init {
        viewModelScope.launch {
            getFavoriteCharacterIdsUseCase.execute().collect { result ->
                result.getDataIfSuccess()?.let { characterIds ->
                    _favouriteCharacterIds.update { characterIds }
                }
            }
        }
    }

}