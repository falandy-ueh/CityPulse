package com.citypulse.app.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citypulse.app.domain.model.Place
import com.citypulse.app.utils.ApiResult
import kotlinx.coroutines.launch

/**
 * TODO Étudiant 3 : Connecter au Repository pour les détails et les favoris.
 * - loadPlaceDetail() : appeler repository.getPlaceDetail(xid)
 * - toggleFavorite() : appeler repository.addFavorite() ou removeFavorite()
 * - buildShareIntent() : créer un Intent.ACTION_SEND avec nom + coordonnées
 */
class DetailViewModel : ViewModel() {

    private val _placeDetail = MutableLiveData<ApiResult<Place>>()
    val placeDetail: LiveData<ApiResult<Place>> = _placeDetail

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _shareIntent = MutableLiveData<Intent?>()
    val shareIntent: LiveData<Intent?> = _shareIntent

    fun loadPlaceDetail(xid: String) {
        _placeDetail.value = ApiResult.Loading
        viewModelScope.launch {
            // TODO Étudiant 3 : _placeDetail.value = repository.getPlaceDetail(xid)
            // Puis : _isFavorite.value = repository.isFavorite(xid)
        }
    }

    fun toggleFavorite(place: Place) {
        viewModelScope.launch {
            // TODO Étudiant 3 : if (isFavorite) removeFavorite else addFavorite
            // Puis : _isFavorite.value = !isFavorite.value!!
        }
    }

    fun buildShareIntent(place: Place) {
        // TODO Étudiant 3 : créer Intent(Intent.ACTION_SEND) avec
        // "Découvrez ${place.name} sur CityPulse ! https://maps.google.com/?q=${place.latitude},${place.longitude}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, place.name)
            putExtra(Intent.EXTRA_TEXT,
                "Découvrez ${place.name} sur CityPulse !\n" +
                "https://maps.google.com/?q=${place.latitude},${place.longitude}")
        }
        _shareIntent.value = Intent.createChooser(intent, "Partager via")
    }
}
