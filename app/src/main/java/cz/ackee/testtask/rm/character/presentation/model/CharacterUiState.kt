package cz.ackee.testtask.rm.character.presentation.model

data class CharacterUiState(
    val id: Int = 0,
    val imageUrl: String? = null,
    val name: String = "",
    val status: String = "",
    val isFavourite: Boolean = false
)
