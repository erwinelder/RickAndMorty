package cz.ackee.testtask.rm.character.data.repository

import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface CharacterRepository {

    suspend fun addFavouriteCharacter(id: Int): ResultData<Unit, CharacterError>

    suspend fun deleteFavouriteCharacter(id: Int): ResultData<Unit, CharacterError>

    suspend fun getCharacters(page: Int): ResultData<List<CharacterDataModel>, CharacterError>

    suspend fun getCharactersByName(
        name: String,
        page: Int
    ): ResultData<List<CharacterDataModel>, CharacterError>

    suspend fun getFavouriteCharacters(): ResultData<List<CharacterDataModel>, CharacterError>

    suspend fun getCharacterById(id: Int): ResultData<CharacterDataModel, CharacterError>

}