package com.citypulse.app.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.citypulse.app.databinding.FragmentListBinding
import com.citypulse.app.viewmodel.ListViewModel

/**
 * TODO Étudiant 4 : Implémenter la liste avec RecyclerView.
 * 1. Créer PlaceAdapter (ViewHolder + DiffUtil.ItemCallback)
 * 2. Observer viewModel.filteredPlaces → mettre à jour l'adapter
 * 3. Connecter la SearchBar à viewModel.search()
 * 4. Ajouter des ChipGroup pour filtrer par catégorie via viewModel.filterByCategory()
 * 5. Gérer les états Loading / Error / Empty dans l'UI
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        // TODO Étudiant 4 : binding.recyclerPlaces.adapter = PlaceAdapter { place -> navigateToDetail(place) }
        // binding.recyclerPlaces.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        // TODO Étudiant 4 : viewModel.filteredPlaces.observe(viewLifecycleOwner) { result -> ... }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
