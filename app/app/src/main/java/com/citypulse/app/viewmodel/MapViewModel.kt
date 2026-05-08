package com.citypulse.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citypulse.app.domain.model.Place
import com.citypulse.app.utils.ApiResult
import kotlinx.coroutines.launch

/**
 * TODO Étudiant 3 : Connecter ce ViewModel au PlaceRepository.
 * - Injecter le repository (utiliser un ViewModelFactory)
 * - Appeler repository.getPlacesNearby() dans searchNearby()
 * - Déclencher la recherche à chaque mise à jour de position (Étudiant 2)
 */
class MapViewModel : ViewModel() {

    private val _places = MutableLiveData<ApiResult<List<Place>>>()
    val places: LiveData<ApiResult<List<Place>>> = _places

    private val _selectedPlace = MutableLiveData<Place?>()
    val selectedPlace: LiveData<Place?> = _selectedPlace

    fun searchNearby(latitude: Double, longitude: Double, radius: Int = 1000, category: String? = null) {
        _places.value = ApiResult.Loading
        viewModelScope.launch {
            // TODO Étudiant 3 : _places.value = repository.getPlacesNearby(latitude, longitude, radius, category)
        }
    }

    fun selectPlace(place: Place) { _selectedPlace.value = place }
    fun clearSelection() { _selectedPlace.value = null }
}
