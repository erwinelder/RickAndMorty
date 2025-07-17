package cz.ackee.testtask.rm.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharacterIdsUseCase
import cz.ackee.testtask.rm.character.mapper.toUiState
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchCharactersViewModel(
    getFavouriteCharacterIdsUseCase: GetFavouriteCharacterIdsUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase
) : ViewModel() {

    private val _favouriteCharacterIds = MutableStateFlow<Set<Int>>(emptySet())


    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.update { query.trimStart() }
    }


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val characters: Flow<PagingData<CharacterUiState>> = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            getCharactersByNameUseCase.execute(name = query).cachedIn(viewModelScope)
        }
        .combine(_favouriteCharacterIds) { pagingData, favouriteIds ->
            pagingData.map { character ->
                character.toUiState(isFavourite = character.id in favouriteIds)
            }
        }


    init {
        viewModelScope.launch {
            getFavouriteCharacterIdsUseCase.execute().getDataIfSuccess()?.let { characterIds ->
                _favouriteCharacterIds.update { characterIds }
            }
        }
    }

}