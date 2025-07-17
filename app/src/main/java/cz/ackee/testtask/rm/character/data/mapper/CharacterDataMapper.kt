package cz.ackee.testtask.rm.character.data.mapper

import cz.ackee.testtask.rm.character.data.model.CharacterDataModel
import cz.ackee.testtask.rm.character.data.model.CharacterLocationDataModel
import cz.ackee.testtask.rm.character.data.model.CharacterOriginDataModel
import cz.ackee.testtask.rm.character.data.remote.model.CharacterDto
import cz.ackee.testtask.rm.character.data.remote.model.CharacterLocationDto
import cz.ackee.testtask.rm.character.data.remote.model.CharacterOriginDto


fun CharacterOriginDto.toDataModel(): CharacterOriginDataModel {
    return CharacterOriginDataModel(
        name = name,
        url = url
    )
}

fun CharacterLocationDto.toDataModel(): CharacterLocationDataModel {
    return CharacterLocationDataModel(
        name = name,
        url = url
    )
}

fun CharacterDto.toDataModel(): CharacterDataModel {
    return CharacterDataModel(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDataModel(),
        location = location.toDataModel(),
        image = image,
        episode = episode,
        url = url,
        created = created
    )
}