package cz.ackee.testtask.rm.character.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginDto,
    val location: CharacterLocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)