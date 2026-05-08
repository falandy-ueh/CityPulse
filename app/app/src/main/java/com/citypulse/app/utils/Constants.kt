package com.citypulse.app.utils

import com.citypulse.app.BuildConfig

object Constants {

    // adresse de base de l'API OpenTripMap
    const val BASE_URL = "https://api.opentripmap.com/0.1/"

    // la clé est stockée dans local.properties et injectée par Gradle, jamais en dur ici
    val OTM_API_KEY: String get() = BuildConfig.OTM_API_KEY

    // rayon de recherche par défaut autour de la position de l'utilisateur
    const val DEFAULT_SEARCH_RADIUS_METERS = 1000
    const val DEFAULT_PLACES_LIMIT = 20

    // intervalles pour les mises à jour de position en arrière-plan
    const val LOCATION_UPDATE_INTERVAL_MS = 10_000L
    const val LOCATION_FASTEST_INTERVAL_MS = 5_000L

    // identifiants des canaux de notification
    const val LOCATION_CHANNEL_ID = "citypulse_location_channel"
    const val ALERT_CHANNEL_ID = "citypulse_alert_channel"
    const val LOCATION_NOTIFICATION_ID = 1001

    // nom et version de la base de données Room
    const val DATABASE_NAME = "citypulse_db"
    const val DATABASE_VERSION = 1

    // clés pour passer les données entre fragments
    const val EXTRA_PLACE_XID = "extra_place_xid"
    const val EXTRA_PLACE_NAME = "extra_place_name"
}
