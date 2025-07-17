package cz.ackee.testtask.rm.character.domain.model.error

import cz.ackee.testtask.rm.request.domain.model.RootError

enum class CharacterError : RootError {
    CharacterNotSaved,
    CharacterNotDeleted,
    CharactersNotFetched,
    CharacterNotFetched,
}