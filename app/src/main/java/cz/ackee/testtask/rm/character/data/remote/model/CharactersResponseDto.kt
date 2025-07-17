package cz.ackee.testtask.rm.character.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    val info: CharactersResponseInfoDto,
    val results: List<CharacterDto>
)
