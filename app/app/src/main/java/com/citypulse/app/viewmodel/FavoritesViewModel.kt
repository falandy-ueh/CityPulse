package com.citypulse.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citypulse.app.domain.model.Place
import kotlinx.coroutines.launch

/**
 * TODO Étudiant 3 : Remplacer _favorites par repository.getFavorites() (LiveData Room).
 * La liste sera automatiquement mise à jour quand Room change.
 */
class FavoritesViewModel : ViewModel() {

    // TODO Étudiant 3 : remplacer par repository.getFavorites()
    private val _favorites = MutableLiveData<List<Place>>(emptyList())
    val favorites: LiveData<List<Place>> = _favorites

    fun removeFavorite(xid: String) {
        viewModelScope.launch {
            // TODO Étudiant 3 : repository.removeFavorite(xid)
        }
    }
}
