package cz.ackee.testtask.rm.character.mapper

import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.presentation.model.CharacterDetailUiState
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState


fun CharacterDataModel.toDomainModel(): Character {
    return Character(
        imageUrl = image,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name
    )
}


fun Character.toUiState(): CharacterUiState {
    return CharacterUiState(
        imageUrl = imageUrl,
        name = name,
        status = status
    )
}

fun Character.toDetailUiState(): CharacterDetailUiState {
    return CharacterDetailUiState(
        imageUrl = imageUrl,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location
    )
}