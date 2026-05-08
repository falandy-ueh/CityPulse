package com.example.citypulse.data.local

import androidx.room.*
import com.example.citypulse.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places")
    fun getAllPlaces(): Flow<List<Place>>

    @Query("SELECT * FROM places WHERE isFavorite = 1")
    fun getFavoritePlaces(): Flow<List<Place>>

    @Query("SELECT * FROM places WHERE id = :id")
    suspend fun getPlaceById(id: String): Place?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(places: List<Place>)

    @Update
    suspend fun updatePlace(place: Place)

    @Delete
    suspend fun deletePlace(place: Place)
    
    @Query("DELETE FROM places WHERE isFavorite = 0")
    suspend fun clearNonFavorites()
}
