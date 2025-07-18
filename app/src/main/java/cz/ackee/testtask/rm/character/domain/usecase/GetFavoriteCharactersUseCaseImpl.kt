package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.character.mapper.toDomainModel
import cz.ackee.testtask.rm.request.domain.model.ResultData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetFavoriteCharactersUseCaseImpl(
    private val repository: CharacterRepository
) : GetFavoriteCharactersUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(): Flow<ResultData<List<Character>, CharacterError>> {
        return repository.getFavoriteCharactersAsFlow().mapLatest { result ->
            result.mapData { characters ->
                characters.map { it.toDomainModel() }
            }
        }
    }

}