package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

class GetFavouriteCharacterIdsUseCaseImpl(
    private val repository: CharacterRepository
) : GetFavouriteCharacterIdsUseCase {

    override suspend fun execute(): ResultData<Set<Int>, CharacterError> {
        return repository.getFavouriteCharacterIds()
    }

}