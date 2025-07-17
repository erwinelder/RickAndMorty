package cz.ackee.testtask.rm.character.mapper

import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.presentation.model.CharacterDetailUiState
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState


fun CharacterDataModel.toDomainModel(): Character {
    return Character(
        id = id,
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


fun Character.toUiState(isFavourite: Boolean): CharacterUiState {
    return CharacterUiState(
        id = id,
        imageUrl = imageUrl,
        name = name,
        status = status,
        isFavourite = isFavourite
    )
}

fun Character.toDetailUiState(isFavourite: Boolean): CharacterDetailUiState {
    return CharacterDetailUiState(
        id = id,
        imageUrl = imageUrl,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        isFavourite = isFavourite
    )
}