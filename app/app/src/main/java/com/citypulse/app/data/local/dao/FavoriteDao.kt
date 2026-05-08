package com.citypulse.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.citypulse.app.data.local.entity.FavoriteEntity

// TODO Étudiant 2 : Implémenter les requêtes Room (les signatures sont prêtes)
@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY savedAt DESC")
    fun getAllFavorites(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE xid = :xid")
    suspend fun getFavoriteById(xid: String): FavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE xid = :xid")
    suspend fun deleteFavoriteById(xid: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE xid = :xid)")
    suspend fun isFavorite(xid: String): Boolean

    @Query("SELECT COUNT(*) FROM favorites")
    suspend fun getFavoritesCount(): Int
}
