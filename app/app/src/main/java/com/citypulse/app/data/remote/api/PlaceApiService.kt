package com.citypulse.app.data.remote.api

import com.citypulse.app.data.remote.model.PlaceDetail
import com.citypulse.app.data.remote.model.PlaceResponse
import com.citypulse.app.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// interface Retrofit pour l'API OpenTripMap (https://opentripmap.io/docs)
// Retrofit génère automatiquement l'implémentation à partir des annotations
interface PlaceApiService {

    // récupère les lieux autour d'une position géographique dans un rayon donné
    // kinds permet de filtrer par catégorie ex: "cultural,natural"
    @GET("en/places/radius")
    suspend fun getPlacesByRadius(
        @Query("radius") radius: Int = Constants.DEFAULT_SEARCH_RADIUS_METERS,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("limit") limit: Int = Constants.DEFAULT_PLACES_LIMIT,
        @Query("offset") offset: Int = 0,
        @Query("kinds") kinds: String? = null,
        @Query("name") name: String? = null,
        @Query("format") format: String = "geojson",
        @Query("apikey") apiKey: String = Constants.OTM_API_KEY
    ): PlaceResponse

    // récupère les infos complètes d'un lieu à partir de son identifiant unique (xid)
    @GET("en/places/xid/{xid}")
    suspend fun getPlaceDetail(
        @Path("xid") xid: String,
        @Query("apikey") apiKey: String = Constants.OTM_API_KEY
    ): PlaceDetail

    // avec format=count l'API retourne juste un entier, sans charger tout le GeoJSON
    @GET("en/places/radius")
    suspend fun getPlacesCount(
        @Query("radius") radius: Int = Constants.DEFAULT_SEARCH_RADIUS_METERS,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("kinds") kinds: String? = null,
        @Query("format") format: String = "count",
        @Query("apikey") apiKey: String = Constants.OTM_API_KEY
    ): Int
}
