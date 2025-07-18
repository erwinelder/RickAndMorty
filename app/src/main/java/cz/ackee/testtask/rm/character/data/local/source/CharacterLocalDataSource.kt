package cz.ackee.testtask.rm.character.data.local.source

import cz.ackee.testtask.rm.character.data.local.model.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {

    suspend fun upsertFavoriteCharacter(character: FavoriteCharacterEntity)

    suspend fun deleteFavoriteCharacter(id: Int)

    fun getFavoriteCharactersAsFlow(): Flow<List<FavoriteCharacterEntity>>

    suspend fun getFavoriteCharacters(): List<FavoriteCharacterEntity>

}