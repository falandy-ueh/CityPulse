package com.citypulse.app.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.citypulse.app.data.local.dao.FavoriteDao
import com.citypulse.app.data.local.entity.FavoriteEntity
import com.citypulse.app.utils.Constants

// TODO Étudiant 2 : Ajouter des entités de cache si besoin (ex: CachedPlaceEntity)
@Database(
    entities = [FavoriteEntity::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class CityPulseDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: CityPulseDatabase? = null

        fun getInstance(context: Context): CityPulseDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CityPulseDatabase::class.java,
                    Constants.DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
    }
}
