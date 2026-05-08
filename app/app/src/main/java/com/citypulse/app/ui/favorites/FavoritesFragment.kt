package com.citypulse.app.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.citypulse.app.databinding.FragmentFavoritesBinding
import com.citypulse.app.viewmodel.FavoritesViewModel

/**
 * TODO Étudiant 4 : Implémenter la liste des favoris.
 * 1. Réutiliser PlaceAdapter depuis ListFragment
 * 2. Observer viewModel.favorites → adapter.submitList()
 * 3. Afficher text_empty_favorites si la liste est vide
 * 4. Ajouter suppression par swipe avec ItemTouchHelper
 *    → onSwiped() : viewModel.removeFavorite(place.xid)
 */
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        // TODO Étudiant 4 : setup RecyclerView + ItemTouchHelper
    }

    private fun observeViewModel() {
        // TODO Étudiant 4 : viewModel.favorites.observe(viewLifecycleOwner) { ... }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
