package com.example.citypulse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Représente un lieu d'intérêt dans l'application.
 * Cette classe sert à la fois d'entité Room et d'objet métier.
 */
@Entity(tableName = "places")
data class Place(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val category: String,       // restaurant, parc, musée, commerce
    val address: String?,
    val photoUrl: String?,
    val userNote: String? = null,
    val isFavorite: Boolean = false
)
