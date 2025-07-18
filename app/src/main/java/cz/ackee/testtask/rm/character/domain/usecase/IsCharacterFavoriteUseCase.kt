package cz.ackee.testtask.rm.character.domain.usecase

interface IsCharacterFavoriteUseCase {

    suspend fun execute(characterId: Int): Boolean

}