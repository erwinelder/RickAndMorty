package cz.ackee.testtask.rm.character.data.local.source

import cz.ackee.testtask.rm.character.data.local.dao.CharacterLocalDao
import cz.ackee.testtask.rm.character.data.local.model.FavoriteCharacterEntity
import cz.ackee.testtask.rm.core.data.local.db.AppDatabase
import kotlinx.coroutines.flow.Flow

class CharacterLocalDataSourceImpl(
    private val dao: CharacterLocalDao
) : CharacterLocalDataSource {

    override suspend fun upsertFavoriteCharacter(character: FavoriteCharacterEntity) {
        dao.upsertFavoriteCharacter(character = character)
    }

    override suspend fun deleteFavoriteCharacter(id: Int) {
        dao.deleteFavoriteCharacter(id = id)
    }

    override fun getFavoriteCharactersAsFlow(): Flow<List<FavoriteCharacterEntity>> {
        return dao.getFavoriteCharactersAsFlow()
    }

    override suspend fun getFavoriteCharacters(): List<FavoriteCharacterEntity> {
        return dao.getFavoriteCharacters()
    }

}

fun characterLocalDataSourceFactory(
    appDatabase: AppDatabase
): CharacterLocalDataSource {
    return CharacterLocalDataSourceImpl(dao = appDatabase.characterDao)
}