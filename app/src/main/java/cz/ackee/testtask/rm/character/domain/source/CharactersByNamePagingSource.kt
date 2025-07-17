package cz.ackee.testtask.rm.character.domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.mapper.toDomainModel
import cz.ackee.testtask.rm.request.domain.model.ResultData

class CharactersByNamePagingSource(
    private val repository: CharacterRepository,
    private val name: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val result = if (name.isNotBlank()) {
                repository.getCharactersByName(name = name, page = page)
            } else {
                ResultData.Success(emptyList())
            }

            val characters = when (result) {
                is ResultData.Success -> result.data.map { it.toDomainModel() }
                is ResultData.Error -> return LoadResult.Error(
                    throwable = Throwable("Failed to load characters: ${result.error.name}")
                )
            }

            LoadResult.Page(
                data = characters,
                prevKey = page.minus(1).takeIf { it > 0 },
                nextKey = if (characters.isNotEmpty()) page.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition = anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition = anchorPosition)?.nextKey?.minus(1)
        }
    }

}