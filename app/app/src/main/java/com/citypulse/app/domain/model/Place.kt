package com.citypulse.app.domain.model

/**
 * Modèle domaine partagé par toutes les couches.
 * Étudiant 1 le peuple via Extensions.kt (toDomainModel).
 * Étudiant 3 l'utilise dans Repository et ViewModels.
 * Étudiant 4 l'affiche dans les Fragments.
 */
data class Place(
    val xid: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val categories: List<String>,
    val rating: Int,
    val imageUrl: String? = null,
    val description: String? = null,
    val address: String? = null,
    val isFavorite: Boolean = false
) {
    val primaryCategory: String
        get() = categories.firstOrNull() ?: "other"

    val displayDistance: String
        get() = if (distance < 1000) "${distance.toInt()} m"
                else "${"%.1f".format(distance / 1000)} km"
}
