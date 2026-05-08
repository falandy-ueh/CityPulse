package com.citypulse.app.data.remote.model

import com.google.gson.annotations.SerializedName

// modèles qui correspondent à la structure JSON renvoyée par l'API (format GeoJSON)
// @SerializedName fait le lien entre le nom JSON et le nom de la variable Kotlin

data class PlaceResponse(
    @SerializedName("type") val type: String,
    @SerializedName("features") val features: List<PlaceFeature> = emptyList()
)

data class PlaceFeature(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("properties") val properties: PlaceProperties
)

// les coordonnées sont dans un tableau [longitude, latitude] dans le JSON
data class Geometry(
    @SerializedName("type") val type: String,
    @SerializedName("coordinates") val coordinates: List<Double>
) {
    val longitude: Double get() = if (coordinates.size >= 2) coordinates[0] else 0.0
    val latitude: Double get() = if (coordinates.size >= 2) coordinates[1] else 0.0
}

data class PlaceProperties(
    @SerializedName("xid") val xid: String,
    @SerializedName("name") val name: String,
    @SerializedName("dist") val distance: Double,
    @SerializedName("rate") val rate: Int,
    @SerializedName("kinds") val kinds: String,
    @SerializedName("osm") val osm: String? = null,
    @SerializedName("wikidata") val wikidata: String? = null
)
