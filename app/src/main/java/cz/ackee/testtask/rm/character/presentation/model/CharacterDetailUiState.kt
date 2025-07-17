package cz.ackee.testtask.rm.character.presentation.model

data class CharacterDetailUiState(
    val id: Int = 0,
    val imageUrl: String? = null,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val isFavourite: Boolean = false
)
