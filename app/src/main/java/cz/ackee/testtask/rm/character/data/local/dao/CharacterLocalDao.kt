package cz.ackee.testtask.rm.character.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.ackee.testtask.rm.character.data.local.model.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterLocalDao {

    @Upsert
    suspend fun upsertFavoriteCharacter(character: FavoriteCharacterEntity)

    @Query("DELETE FROM favorite_character WHERE id = :id")
    suspend fun deleteFavoriteCharacter(id: Int)

    @Query("SELECT * FROM favorite_character")
    fun getFavoriteCharactersAsFlow(): Flow<List<FavoriteCharacterEntity>>

    @Query("SELECT * FROM favorite_character")
    suspend fun getFavoriteCharacters(): List<FavoriteCharacterEntity>

}