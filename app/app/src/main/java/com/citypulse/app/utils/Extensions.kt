package com.citypulse.app.utils

import com.citypulse.app.data.remote.model.PlaceDetail
import com.citypulse.app.data.remote.model.PlaceFeature
import com.citypulse.app.domain.model.Place

// convertit un élément de la liste renvoyée par l'API vers notre modèle interne
fun PlaceFeature.toDomainModel(): Place = Place(
    xid = properties.xid,
    name = properties.name.ifBlank { "Lieu sans nom" },
    latitude = geometry.latitude,
    longitude = geometry.longitude,
    distance = properties.distance,
    // l'API renvoie les catégories séparées par des virgules, on les découpe en liste
    categories = properties.kinds.split(",").map { it.trim() }.filter { it.isNotEmpty() },
    rating = properties.rate,
    imageUrl = null,
    description = null,
    address = null,
    isFavorite = false
)

// même chose mais pour la réponse détaillée d'un seul lieu
fun PlaceDetail.toDomainModel(): Place = Place(
    xid = xid,
    name = name.ifBlank { "Lieu sans nom" },
    latitude = point.latitude,
    longitude = point.longitude,
    distance = 0.0,
    categories = kinds.split(",").map { it.trim() }.filter { it.isNotEmpty() },
    // le champ rate peut contenir "3h" ou "3" selon le lieu, on extrait juste le chiffre
    rating = rate?.filter { it.isDigit() }?.toIntOrNull() ?: 0,
    imageUrl = preview?.source ?: image,
    description = wikipediaExtracts?.text,
    address = address?.toFormattedString(),
    isFavorite = false
)
