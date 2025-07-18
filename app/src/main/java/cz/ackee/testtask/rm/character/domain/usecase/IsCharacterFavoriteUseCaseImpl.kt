package cz.ackee.testtask.rm.character.domain.usecase

import cz.ackee.testtask.rm.character.data.repository.CharacterRepository

class IsCharacterFavoriteUseCaseImpl(
    private val repository: CharacterRepository
) : IsCharacterFavoriteUseCase {

    override suspend fun execute(characterId: Int): Boolean {
        val ids = repository.getFavoriteCharacterIds().getDataIfSuccess() ?: return false
        return characterId in ids
    }

}