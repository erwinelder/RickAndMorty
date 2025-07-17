package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.character.mapper.toDomainModel
import cz.ackee.testtask.rm.request.domain.model.ResultData

class GetCharactersUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharactersUseCase {

    override suspend fun execute(page: Int): ResultData<List<Character>, CharacterError> {
        return repository.getCharacters(page = page).mapData { characters ->
            characters.map { it.toDomainModel() }
        }
    }

}