package com.citypulse.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citypulse.app.domain.model.Place
import com.citypulse.app.utils.ApiResult
import kotlinx.coroutines.launch

/**
 * TODO Étudiant 3 : Implémenter le filtrage, la recherche textuelle et le calcul de distances.
 * - filterByCategory() : filtrer _allPlaces par catégorie
 * - search() : filtrer par nom (Place.name.contains(query, ignoreCase = true))
 * - sortByDistance() : trier par distance calculée (Haversine ou Place.distance)
 */
class ListViewModel : ViewModel() {

    private val _allPlaces = mutableListOf<Place>()

    private val _filteredPlaces = MutableLiveData<ApiResult<List<Place>>>()
    val filteredPlaces: LiveData<ApiResult<List<Place>>> = _filteredPlaces

    private var currentQuery: String = ""
    private var currentCategory: String? = null

    fun loadPlaces(latitude: Double, longitude: Double, radius: Int = 1000) {
        _filteredPlaces.value = ApiResult.Loading
        viewModelScope.launch {
            // TODO Étudiant 3 : appeler repository.getPlacesNearby() et mettre à jour _allPlaces
        }
    }

    fun filterByCategory(category: String?) {
        currentCategory = category
        applyFilters()
    }

    fun search(query: String) {
        currentQuery = query
        applyFilters()
    }

    private fun applyFilters() {
        // TODO Étudiant 3 : filtrer _allPlaces par currentCategory et currentQuery
    }

    fun sortByDistance(userLatitude: Double, userLongitude: Double) {
        // TODO Étudiant 3 : trier _allPlaces par distance calculée
    }
}
