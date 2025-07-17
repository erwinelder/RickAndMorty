package cz.ackee.testtask.rm.character.data.local.source

import cz.ackee.testtask.rm.character.data.local.model.FavouriteCharacterEntity

interface CharacterLocalDataSource {

    suspend fun upsertFavoriteCharacter(character: FavouriteCharacterEntity)

    suspend fun deleteFavoriteCharacter(id: Int)

    suspend fun getFavoriteCharacters(): List<FavouriteCharacterEntity>

}