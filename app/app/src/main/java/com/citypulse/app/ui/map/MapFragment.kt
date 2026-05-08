package com.citypulse.app.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.citypulse.app.databinding.FragmentMapBinding
import com.citypulse.app.viewmodel.MapViewModel

/**
 * TODO Étudiant 4 : Intégrer Google Maps SDK.
 * 1. Récupérer le SupportMapFragment et appeler getMapAsync()
 * 2. Dans onMapReady(), observer viewModel.places et ajouter des Markers
 * 3. Clic sur Marker → viewModel.selectPlace() + afficher InfoWindow
 * 4. FAB "Ma position" → déplacer la caméra sur la position actuelle
 * 5. Observer la position de l'Étudiant 2 pour déclencher viewModel.searchNearby()
 */
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO Étudiant 4 : Initialiser Google Maps + observers ici
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
