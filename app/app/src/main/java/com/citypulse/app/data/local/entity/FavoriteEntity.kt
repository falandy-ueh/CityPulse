package com.citypulse.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO Étudiant 2 : Vérifier que tous les champs sont bien persistés
// Ajouter d'éventuels champs supplémentaires si nécessaire (ex: osmId, wikipediaUrl)
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val xid: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val categories: String,     // Stocké comme "cat1,cat2,cat3"
    val rating: Int,
    val imageUrl: String?,
    val address: String?,
    val savedAt: Long = System.currentTimeMillis()
)
