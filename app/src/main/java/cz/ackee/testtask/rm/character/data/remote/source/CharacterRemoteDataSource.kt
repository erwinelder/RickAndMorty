package cz.ackee.testtask.rm.character.data.remote.source

import cz.ackee.testtask.rm.character.data.remote.model.CharacterDto
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface CharacterRemoteDataSource {

    suspend fun getCharacters(page: Int): ResultData<List<CharacterDto>, CharacterError>

    suspend fun getCharactersByName(
        name: String,
        page: Int
    ): ResultData<List<CharacterDto>, CharacterError>

    suspend fun getCharactersByIds(
        ids: List<Int>
    ): ResultData<List<CharacterDto>, CharacterError>

    suspend fun getCharacterById(id: Int): ResultData<CharacterDto, CharacterError>

}