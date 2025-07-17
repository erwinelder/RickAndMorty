package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.request.domain.model.ResultData

interface GetCharactersByNameUseCase {

    suspend fun execute(name: String, page: Int): ResultData<List<Character>, CharacterError>

}