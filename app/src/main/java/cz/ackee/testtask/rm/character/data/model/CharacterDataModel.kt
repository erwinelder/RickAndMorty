package cz.ackee.testtask.rm.character.data.model

data class CharacterDataModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginDataModel,
    val location: CharacterLocationDataModel,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)