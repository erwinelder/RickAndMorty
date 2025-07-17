package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface DeleteFavouriteCharacterUseCase {

    suspend fun execute(characterId: Int): ResultData<Unit, CharacterError>

}