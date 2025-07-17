package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.character.mapper.toDomainModel
import cz.ackee.testtask.rm.request.domain.model.ResultData

class GetCharacterByIdUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterByIdUseCase {

    override suspend fun execute(id: Int): ResultData<Character, CharacterError> {
        return repository.getCharacterById(id = id).mapData { it.toDomainModel() }
    }

}