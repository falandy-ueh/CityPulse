package com.example.citypulse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citypulse.data.PlaceRepository
import com.example.citypulse.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.location.Location

class CityViewModel(private val repository: PlaceRepository) : ViewModel() {

    // Liste des lieux que l'UI (Étudiant 4) va afficher
    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    // 1. Charger les lieux (Gère le mode hors-ligne via le Repository)
    fun chargerLieux(lat: Double, lon: Double) {
        viewModelScope.launch {
            val result = repository.obtenirLieux(lat, lon)
            _places.value = result
        }
    }

    // 2. Logique de filtrage par catégorie (Exigence 2.2 du sujet)
    fun filtrerParCategorie(categorie: String) {
        val listeActuelle = _places.value
        _places.value = listeActuelle.filter { it.category == categorie }
    }

    // 3. Logique de calcul de distance (Exigence 2.1 du sujet)
    fun calculerDistance(userLat: Double, userLon: Double, placeLat: Double, placeLon: Double): Float {
        val userLoc = Location("user").apply {
            latitude = userLat
            longitude = userLon
        }
        val placeLoc = Location("place").apply {
            latitude = placeLat
            longitude = placeLon
        }
        // Retourne la distance en mètres entre l'utilisateur et le lieu
        return userLoc.distanceTo(placeLoc)
    }
}