package cz.ackee.testtask.rm.character.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_character")
data class FavouriteCharacterEntity(
    @PrimaryKey
    val id: Int
)
