package cz.ackee.testtask.rm.character.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
