package cz.ackee.testtask.rm.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ackee.testtask.rm.character.domain.usecase.AddFavoriteCharacterUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharacterByIdUseCase
import cz.ackee.testtask.rm.character.domain.usecase.IsCharacterFavoriteUseCase
import cz.ackee.testtask.rm.character.domain.usecase.RemoveFavoriteCharacterUseCase
import cz.ackee.testtask.rm.character.mapper.toDetailUiState
import cz.ackee.testtask.rm.character.presentation.model.CharacterDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    id: Int,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val removeFavoriteCharacterUseCase: RemoveFavoriteCharacterUseCase,
) : ViewModel() {

    private val _character = MutableStateFlow(CharacterDetailUiState())
    val character = _character.asStateFlow()


    fun changeFavouriteStatus() {
        val isFavourite = _character.value.isFavourite
        val characterId = _character.value.id

        viewModelScope.launch {
            if (isFavourite) {
                removeFavoriteCharacterUseCase.execute(characterId = characterId)
            } else {
                addFavoriteCharacterUseCase.execute(characterId = characterId)
            }

            _character.update {
                it.copy(isFavourite = !isFavourite)
            }
        }
    }


    init {
        viewModelScope.launch {
            val isFavourite = isCharacterFavoriteUseCase.execute(characterId = id)
            val character = getCharacterByIdUseCase.execute(id = id).getDataIfSuccess()
                ?.toDetailUiState(isFavourite = isFavourite)
                ?: return@launch
            _character.update { character }
        }
    }

}