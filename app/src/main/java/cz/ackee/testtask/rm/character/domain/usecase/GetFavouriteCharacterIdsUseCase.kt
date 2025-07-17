package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface GetFavouriteCharacterIdsUseCase {

    suspend fun execute(): ResultData<Set<Int>, CharacterError>

}