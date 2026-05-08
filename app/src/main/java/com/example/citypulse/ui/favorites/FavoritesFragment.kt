package com.example.citypulse.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citypulse.R
import com.example.citypulse.databinding.FragmentFavoritesBinding
import com.example.citypulse.model.Place
import com.example.citypulse.utils.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment affichant les lieux favoris de l'utilisateur.
 * Gère le swipe-to-delete et l'affichage d'un état vide premium.
 */
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoritesAdapter: FavoritesAdapter
    private var favoritesList = mutableListOf<Place>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()

        // Simulation de données pour la démo
        loadMockFavorites()
    }

    private fun setupRecyclerView() {
        favoritesAdapter = FavoritesAdapter { place ->
            // Navigation vers le détail
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToPlaceDetailFragment(place.id)
            findNavController().navigate(action)
        }

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesAdapter
        }

        // Configuration du Swipe Actions
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val place = favoritesList[position]

                if (direction == ItemTouchHelper.LEFT) {
                    // Supprimer des favoris
                    removeFavorite(place, position)
                } else if (direction == ItemTouchHelper.RIGHT) {
                    // Voir sur la carte (Logique de navigation)
                    favoritesAdapter.notifyItemChanged(position) // Reset swipe
                    findNavController().navigate(R.id.mapFragment)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvFavorites)
    }

    private fun setupListeners() {
        binding.layoutEmptyState.btnExplore.setOnClickListener {
            findNavController().navigate(R.id.placeListFragment)
        }
    }

    private fun loadMockFavorites() {
        favoritesList = mutableListOf(
            Place("2", "Parc Central", 48.8584, 2.2945, "Parcs", "Avenue Gustave Eiffel", null, isFavorite = true),
            Place("4", "Café de Flore", 48.8542, 2.3331, "Restaurants", "172 Bd Saint-Germain", null, isFavorite = true)
        )
        updateUI()
    }

    private fun removeFavorite(place: Place, position: Int) {
        favoritesList.removeAt(position)
        favoritesAdapter.submitList(favoritesList.toList())
        updateHeader()
        checkEmptyState()

        Snackbar.make(binding.root, "Lieu retiré des favoris", Snackbar.LENGTH_LONG)
            .setAction("ANNULER") {
                favoritesList.add(position, place)
                favoritesAdapter.submitList(favoritesList.toList())
                updateHeader()
                checkEmptyState()
            }
            .setActionTextColor(resources.getColor(R.color.secondary, null))
            .show()
    }

    private fun updateUI() {
        favoritesAdapter.submitList(favoritesList.toList())
        updateHeader()
        checkEmptyState()
    }

    private fun updateHeader() {
        binding.tvFavoritesCount.text = "${favoritesList.size} lieux sauvegardés"
    }

    private fun checkEmptyState() {
        if (favoritesList.isEmpty()) {
            binding.layoutEmptyState.emptyStateRoot.visibility = View.VISIBLE
            binding.rvFavorites.visibility = View.GONE
            binding.tvFavoritesCount.visibility = View.GONE
        } else {
            binding.layoutEmptyState.emptyStateRoot.visibility = View.GONE
            binding.rvFavorites.visibility = View.VISIBLE
            binding.tvFavoritesCount.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
