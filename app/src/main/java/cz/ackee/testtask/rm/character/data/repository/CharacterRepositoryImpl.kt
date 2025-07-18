package cz.ackee.testtask.rm.character.data.repository

import cz.ackee.testtask.rm.character.data.local.model.FavoriteCharacterEntity
import cz.ackee.testtask.rm.character.data.local.source.CharacterLocalDataSource
import cz.ackee.testtask.rm.character.data.mapper.toDataModel
import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSource
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class CharacterRepositoryImpl(
    private val localSource: CharacterLocalDataSource,
    private val remoteSource: CharacterRemoteDataSource
) : CharacterRepository {

    override suspend fun addFavoriteCharacter(
        id: Int
    ): ResultData<Unit, CharacterError> {
        return try {
            val character = FavoriteCharacterEntity(id = id)
            localSource.upsertFavoriteCharacter(character = character)
            ResultData.Success(Unit)
        } catch (_: Exception) {
            ResultData.Error(CharacterError.CharacterNotSaved)
        }
    }

    override suspend fun deleteFavoriteCharacter(id: Int): ResultData<Unit, CharacterError> {
        return try {
            localSource.deleteFavoriteCharacter(id = id)
            ResultData.Success(Unit)
        } catch (_: Exception) {
            ResultData.Error(CharacterError.CharacterNotDeleted)
        }
    }

    override suspend fun getCharacters(
        page: Int
    ): ResultData<List<CharacterDataModel>, CharacterError> {
        return remoteSource.getCharacters(page = page).mapData { characters ->
            characters.map { it.toDataModel() }
        }
    }

    override suspend fun getCharactersByName(
        name: String,
        page: Int
    ): ResultData<List<CharacterDataModel>, CharacterError> {
        return remoteSource.getCharactersByName(name = name, page = page).mapData { characters ->
            characters.map { it.toDataModel() }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFavoriteCharactersAsFlow(
    ): Flow<ResultData<List<CharacterDataModel>, CharacterError>> {
        return localSource.getFavoriteCharactersAsFlow().mapLatest { characters ->
            if (characters.isEmpty()) {
                return@mapLatest ResultData.Success(emptyList())
            }

            val ids = characters.map { it.id }
            remoteSource.getCharactersByIds(ids = ids).mapData { remoteCharacters ->
                remoteCharacters.map { it.toDataModel() }
            }
        }.catch { exception ->
            emit(ResultData.Error(CharacterError.FavouriteCharactersNotFetched))
        }
    }

    override fun getFavoriteCharacterIdsAsFlow(): Flow<ResultData<Set<Int>, CharacterError>> {
        return localSource.getFavoriteCharactersAsFlow()
            .map <List<FavoriteCharacterEntity>, ResultData<Set<Int>, CharacterError>> { characters ->
                ResultData.Success(
                    data = characters.map { it.id }.toSet()
                )
            }
            .catch { exception ->
                emit(ResultData.Error(CharacterError.FavouriteCharactersNotFetched))
            }
    }

    override suspend fun getFavoriteCharacterIds(): ResultData<Set<Int>, CharacterError> {
        return try {
            ResultData.Success(
                data = localSource.getFavoriteCharacters().map { it.id }.toSet()
            )
        } catch (_: Exception) {
            ResultData.Error(CharacterError.FavouriteCharactersNotFetched)
        }
    }

    override suspend fun getCharacterById(id: Int): ResultData<CharacterDataModel, CharacterError> {
        return remoteSource.getCharacterById(id = id).mapData { character ->
            character.toDataModel()
        }
    }

}