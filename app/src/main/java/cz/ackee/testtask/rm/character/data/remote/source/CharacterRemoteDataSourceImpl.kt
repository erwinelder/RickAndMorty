package cz.ackee.testtask.rm.character.data.remote.source

import cz.ackee.testtask.rm.character.data.remote.model.CharacterDto
import cz.ackee.testtask.rm.character.data.remote.model.CharactersResponseDto
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.core.data.remote.apiUrl
import cz.ackee.testtask.rm.request.domain.model.ResultData
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class CharacterRemoteDataSourceImpl(
    private val client: HttpClient
) : CharacterRemoteDataSource {

    override suspend fun getCharacters(page: Int): ResultData<List<CharacterDto>, CharacterError> {
        val response = try {
            client.get(
                urlString = "$apiUrl/character/?page=$page"
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (_: Exception) {
            return ResultData.Error(CharacterError.CharactersNotFetched)
        }

        return when (response.status) {
            HttpStatusCode.OK -> ResultData.Success(
                data = Json
                    .decodeFromString<CharactersResponseDto>(string = response.bodyAsText())
                    .results
            )
            else -> ResultData.Error(CharacterError.CharactersNotFetched)
        }
    }

    override suspend fun getCharactersByName(
        name: String,
        page: Int
    ): ResultData<List<CharacterDto>, CharacterError> {
        val response = try {
            client.get(
                urlString = "$apiUrl/character/?page=$page&name=$name"
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (_: Exception) {
            return ResultData.Error(CharacterError.CharactersNotFetched)
        }

        return when (response.status) {
            HttpStatusCode.OK -> ResultData.Success(
                data = Json
                    .decodeFromString<CharactersResponseDto>(string = response.bodyAsText())
                    .results
            )
            else -> ResultData.Error(CharacterError.CharactersNotFetched)
        }
    }

    override suspend fun getCharactersByIds(
        ids: List<Int>
    ): ResultData<List<CharacterDto>, CharacterError> {
        val ids = ids.joinToString(separator = ",")
        val response = try {
            client.get(
                urlString = "$apiUrl/character/[$ids]"
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (_: Exception) {
            return ResultData.Error(CharacterError.CharactersNotFetched)
        }

        return when (response.status) {
            HttpStatusCode.OK -> ResultData.Success(
                data = Json.decodeFromString<List<CharacterDto>>(string = response.bodyAsText())
            )
            else -> ResultData.Error(CharacterError.CharactersNotFetched)
        }
    }

    override suspend fun getCharacterById(id: Int): ResultData<CharacterDto, CharacterError> {
        val response = try {
            client.get(
                urlString = "$apiUrl/character/$id"
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (_: Exception) {
            return ResultData.Error(CharacterError.CharacterNotFetched)
        }

        return when (response.status) {
            HttpStatusCode.OK -> ResultData.Success(
                data = Json.decodeFromString<CharacterDto>(string = response.bodyAsText())
            )
            else -> ResultData.Error(CharacterError.CharacterNotFetched)
        }
    }

}