package cz.ackee.testtask.rm.character.data.local.source

import cz.ackee.testtask.rm.character.data.local.dao.CharacterLocalDao
import cz.ackee.testtask.rm.character.data.local.model.FavouriteCharacterEntity
import cz.ackee.testtask.rm.core.data.local.db.AppDatabase

class CharacterLocalDataSourceImpl(
    private val dao: CharacterLocalDao
) : CharacterLocalDataSource {

    override suspend fun upsertFavoriteCharacter(character: FavouriteCharacterEntity) {
        dao.upsertFavouriteCharacter(character = character)
    }

    override suspend fun deleteFavoriteCharacter(id: Int) {
        dao.deleteFavouriteCharacter(id = id)
    }

    override suspend fun getFavoriteCharacters(): List<FavouriteCharacterEntity> {
        return dao.getFavouriteCharacters()
    }

}

fun characterLocalDataSourceFactory(
    appDatabase: AppDatabase
): CharacterLocalDataSource {
    return CharacterLocalDataSourceImpl(dao = appDatabase.characterDao)
}