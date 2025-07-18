package cz.ackee.testtask.rm.core.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.ackee.testtask.rm.character.data.local.dao.CharacterLocalDao
import cz.ackee.testtask.rm.character.data.local.model.FavoriteCharacterEntity

@Database(
    entities = [
        FavoriteCharacterEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract val characterDao: CharacterLocalDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {

                Room
                    .databaseBuilder(
                        context, AppDatabase::class.java, "app_data"
                    )
                    .build()
                    .also { Instance = it }

            }
        }
    }
}