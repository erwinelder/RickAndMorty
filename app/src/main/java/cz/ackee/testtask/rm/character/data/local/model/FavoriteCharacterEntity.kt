package cz.ackee.testtask.rm.character.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_character")
data class FavoriteCharacterEntity(
    @PrimaryKey
    val id: Int
)
