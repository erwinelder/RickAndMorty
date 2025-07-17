package cz.ackee.testtask.rm.character.domain.model

data class Character(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String
)
