package cz.ackee.testtask.rm.character.data.local.model

import androidx.room.Entity

@Entity(tableName = "favourite_character")
data class FavouriteCharacterEntity(
    val id: Int
)
