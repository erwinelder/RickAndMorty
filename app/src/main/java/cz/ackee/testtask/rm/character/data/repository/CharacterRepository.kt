package cz.ackee.testtask.rm.character.data.repository

import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun addFavoriteCharacter(id: Int): ResultData<Unit, CharacterError>

    suspend fun deleteFavoriteCharacter(id: Int): ResultData<Unit, CharacterError>

    suspend fun getCharacters(page: Int): ResultData<List<CharacterDataModel>, CharacterError>

    suspend fun getCharactersByName(
        name: String,
        page: Int
    ): ResultData<List<CharacterDataModel>, CharacterError>

    fun getFavoriteCharactersAsFlow(): Flow<ResultData<List<CharacterDataModel>, CharacterError>>

    fun getFavoriteCharacterIdsAsFlow(): Flow<ResultData<Set<Int>, CharacterError>>

    suspend fun getFavoriteCharacterIds(): ResultData<Set<Int>, CharacterError>

    suspend fun getCharacterById(id: Int): ResultData<CharacterDataModel, CharacterError>

}