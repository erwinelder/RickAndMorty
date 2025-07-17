package cz.ackee.testtask.rm.character.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.source.CharactersPagingSource
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharactersUseCase {

    override fun execute(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(repository = repository) }
        ).flow
    }

}