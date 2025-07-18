package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData
import kotlinx.coroutines.flow.Flow

interface GetFavoriteCharacterIdsUseCase {

    suspend fun execute(): Flow<ResultData<Set<Int>, CharacterError>>

}