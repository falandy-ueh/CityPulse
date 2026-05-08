package com.citypulse.app.data.remote.model

import com.google.gson.annotations.SerializedName

// réponse complète pour un seul lieu, renvoyée par l'endpoint /xid/{xid}
// beaucoup de champs sont nullable car l'API ne les remplit pas toujours
data class PlaceDetail(
    @SerializedName("xid") val xid: String,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: Address? = null,
    @SerializedName("rate") val rate: String? = null,
    @SerializedName("kinds") val kinds: String = "",
    @SerializedName("image") val image: String? = null,
    @SerializedName("preview") val preview: Preview? = null,
    @SerializedName("wikipedia_extracts") val wikipediaExtracts: WikipediaExtracts? = null,
    @SerializedName("point") val point: Point,
    @SerializedName("osm") val osm: String? = null,
    @SerializedName("otm") val otm: String? = null,
    @SerializedName("wikipedia") val wikipedia: String? = null
)

data class Address(
    @SerializedName("city") val city: String? = null,
    @SerializedName("road") val road: String? = null,
    @SerializedName("house_number") val houseNumber: String? = null,
    @SerializedName("postcode") val postcode: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("country_code") val countryCode: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("suburb") val suburb: String? = null
) {
    // assemble les champs non-null pour afficher une adresse lisible
    fun toFormattedString(): String {
        val parts = listOfNotNull(
            listOfNotNull(houseNumber, road).joinToString(" ").ifBlank { null },
            city,
            postcode,
            country
        )
        return parts.joinToString(", ")
    }
}

data class Preview(
    @SerializedName("source") val source: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
)

data class WikipediaExtracts(
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String? = null,
    @SerializedName("html") val html: String? = null
)

data class Point(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)
