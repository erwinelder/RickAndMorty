package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData
import kotlinx.coroutines.flow.Flow

class GetFavoriteCharacterIdsUseCaseImpl(
    private val repository: CharacterRepository
) : GetFavoriteCharacterIdsUseCase {

    override suspend fun execute(): Flow<ResultData<Set<Int>, CharacterError>> {
        return repository.getFavoriteCharacterIdsAsFlow()
    }

}