package cz.ackee.testtask.rm.character.data.repository

import cz.ackee.testtask.rm.character.data.local.model.FavouriteCharacterEntity
import cz.ackee.testtask.rm.character.data.local.source.CharacterLocalDataSource
import cz.ackee.testtask.rm.character.data.mapper.toDataModel
import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSource
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

class CharacterRepositoryImpl(
    private val localSource: CharacterLocalDataSource,
    private val remoteSource: CharacterRemoteDataSource
) : CharacterRepository {

    override suspend fun addFavouriteCharacter(
        id: Int
    ): ResultData<Unit, CharacterError> {
        return try {
            val character = FavouriteCharacterEntity(id = id)
            localSource.upsertFavoriteCharacter(character = character)
            ResultData.Success(Unit)
        } catch (_: Exception) {
            ResultData.Error(CharacterError.CharacterNotSaved)
        }
    }

    override suspend fun deleteFavouriteCharacter(id: Int): ResultData<Unit, CharacterError> {
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

    override suspend fun getFavouriteCharacters(
    ): ResultData<List<CharacterDataModel>, CharacterError> {
        val ids = localSource.getFavoriteCharacters().map { it.id }
        return remoteSource.getCharactersByIds(ids = ids).mapData { characters ->
            characters.map { it.toDataModel() }
        }
    }

    override suspend fun getCharacterById(id: Int): ResultData<CharacterDataModel, CharacterError> {
        return remoteSource.getCharacterById(id = id).mapData { character ->
            character.toDataModel()
        }
    }

}