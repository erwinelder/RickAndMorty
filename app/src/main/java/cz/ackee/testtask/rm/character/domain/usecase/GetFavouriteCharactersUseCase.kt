package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface GetFavouriteCharactersUseCase {

    suspend fun execute(): ResultData<List<Character>, CharacterError>

}