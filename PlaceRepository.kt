package com.citypulse.app.data.repository

import androidx.lifecycle.LiveData
import com.citypulse.app.data.local.database.CityPulseDatabase
import com.citypulse.app.data.remote.network.NetworkModule
import com.citypulse.app.domain.model.Place
import com.citypulse.app.utils.ApiResult
import com.citypulse.app.utils.safeApiCall
import com.citypulse.app.utils.toDomainModel

/**
 * TODO Étudiant 3 : Ce Repository est la Source Unique de Vérité (Single Source of Truth).
 * Il orchestre :
 *  - Les appels réseau (NetworkModule — Étudiant 1)
 *  - La persistance locale (CityPulseDatabase — Étudiant 2)
 *  - La logique hors-ligne (retourner le cache Room si pas de réseau)
 *
 * Les méthodes réseau sont déjà connectées via safeApiCall.
 * Compléter getFavorites() et addFavorite() avec le mapping Entity <-> Place.
 */
class PlaceRepository(private val database: CityPulseDatabase) {

    private val apiService = NetworkModule.placeApiService
    private val favoriteDao = database.favoriteDao()

    // ─── Appels réseau (Étudiant 1 fournit apiService) ───────────────────────────

    suspend fun getPlacesNearby(
        latitude: Double,
        longitude: Double,
        radius: Int = 1000,
        category: String? = null
    ): ApiResult<List<Place>> = safeApiCall {
        apiService.getPlacesByRadius(
            latitude = latitude,
            longitude = longitude,
            radius = radius,
            kinds = category
        ).features.map { it.toDomainModel() }
    }

    suspend fun getPlaceDetail(xid: String): ApiResult<Place> = safeApiCall {
        apiService.getPlaceDetail(xid).toDomainModel()
    }

    suspend fun searchPlaces(
        latitude: Double,
        longitude: Double,
        query: String,
        radius: Int = 5000
    ): ApiResult<List<Place>> = safeApiCall {
        apiService.getPlacesByRadius(
            latitude = latitude,
            longitude = longitude,
            radius = radius,
            name = query
        ).features.map { it.toDomainModel() }
    }

    // ─── Favoris (Étudiant 2 fournit favoriteDao) ────────────────────────────────

    fun getFavorites(): LiveData<List<Place>> {
        // TODO Étudiant 3 : Transformer LiveData<List<FavoriteEntity>> en LiveData<List<Place>>
        // Utiliser map { entity -> Place(xid = entity.xid, ...) }
        TODO("À implémenter : map FavoriteEntity -> Place via Transformations.map()")
    }

    suspend fun addFavorite(place: Place) {
        // TODO Étudiant 3 : Convertir Place -> FavoriteEntity et appeler favoriteDao.insertFavorite()
        TODO("À implémenter : créer FavoriteEntity depuis Place")
    }

    suspend fun removeFavorite(xid: String) {
        favoriteDao.deleteFavoriteById(xid)
    }

    suspend fun isFavorite(xid: String): Boolean = favoriteDao.isFavorite(xid)
}
