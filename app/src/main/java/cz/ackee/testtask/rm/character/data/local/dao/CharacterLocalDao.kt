package cz.ackee.testtask.rm.character.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.ackee.testtask.rm.character.data.local.model.FavouriteCharacterEntity

@Dao
interface CharacterLocalDao {

    @Upsert
    suspend fun upsertFavouriteCharacter(character: FavouriteCharacterEntity)

    @Query("DELETE FROM favourite_character WHERE id = :id")
    suspend fun deleteFavouriteCharacter(id: Int)

    @Query("SELECT * FROM favourite_character")
    suspend fun getFavouriteCharacters(): List<FavouriteCharacterEntity>

}