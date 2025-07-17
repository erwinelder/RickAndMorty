package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

class DeleteFavouriteCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : DeleteFavouriteCharacterUseCase {

    override suspend fun execute(characterId: Int): ResultData<Unit, CharacterError> {
        return repository.deleteFavouriteCharacter(id = characterId)
    }

}