package cz.ackee.testtask.rm.character.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationDto(
    val name: String,
    val url: String
)
