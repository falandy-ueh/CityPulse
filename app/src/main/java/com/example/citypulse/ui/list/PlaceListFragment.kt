package com.example.citypulse.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citypulse.databinding.FragmentPlaceListBinding
import com.example.citypulse.model.Place

/**
 * Fragment affichant la liste des lieux d'intérêt avec filtres et recherche.
 * Design : Material 3, Shimmer Effect pour le chargement.
 */
class PlaceListFragment : Fragment() {

    private var _binding: FragmentPlaceListBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var placeAdapter: PlaceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupFilters()
        
        // Simule un état de chargement avec Shimmer
        showLoading(true)
        
        // Simule la fin du chargement après 2 secondes
        view.postDelayed({
            showLoading(false)
            // Données de test
            val dummyPlaces = listOf(
                Place("1", "Le Gourmet", 48.8566, 2.3522, "Restaurants", "12 Rue de Rivoli", null),
                Place("2", "Parc Central", 48.8584, 2.2945, "Parcs", "Avenue Gustave Eiffel", null, isFavorite = true),
                Place("3", "Musée d'Art", 48.8606, 2.3376, "Musées", "Palais du Louvre", null)
            )
            placeAdapter.submitList(dummyPlaces)
        }, 2000)

        binding.btnRetry.setOnClickListener {
            showLoading(true)
            // Logique de rechargement ici
        }
    }

    private fun setupRecyclerView() {
        placeAdapter = PlaceListAdapter(
            onPlaceClick = { place ->
                // Navigation vers le détail (à implémenter)
            },
            onFavoriteClick = { place ->
                // Logique favoris via ViewModel (à implémenter)
            }
        )
        
        binding.rvPlaces.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = placeAdapter
        }
    }

    private fun setupFilters() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            // Logique de filtrage par catégorie
        }
    }

    /**
     * Gère l'affichage du Shimmer Effect pendant le chargement.
     */
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.rvPlaces.visibility = View.GONE
        } else {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            binding.rvPlaces.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
