package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

class AddFavoriteCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : AddFavoriteCharacterUseCase {

    override suspend fun execute(characterId: Int): ResultData<Unit, CharacterError> {
        return repository.addFavoriteCharacter(id = characterId)
    }

}