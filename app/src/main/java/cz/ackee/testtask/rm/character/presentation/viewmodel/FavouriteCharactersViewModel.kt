package cz.ackee.testtask.rm.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharactersUseCase
import cz.ackee.testtask.rm.character.mapper.toUiState
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteCharactersViewModel(
    private val getFavouriteCharactersUseCase: GetFavouriteCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterUiState>>(emptyList())
    val characters = _characters.asStateFlow()


    init {
        viewModelScope.launch {
            val characters = getFavouriteCharactersUseCase.execute().getDataIfSuccess()
                ?.map { it.toUiState(isFavourite = true) }
                ?: emptyList()
            _characters.update { characters }
        }
    }

}