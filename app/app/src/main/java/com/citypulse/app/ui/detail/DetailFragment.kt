package com.citypulse.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.citypulse.app.databinding.FragmentDetailBinding
import com.citypulse.app.viewmodel.DetailViewModel

/**
 * TODO Étudiant 4 : Implémenter l'écran détail.
 * 1. Récupérer le xid depuis les args Navigation (activer val args: DetailFragmentArgs by navArgs())
 * 2. Appeler viewModel.loadPlaceDetail(args.xid) dans onViewCreated
 * 3. Observer placeDetail → remplir image (Glide/Picasso), nom, adresse, description
 * 4. Observer isFavorite → changer l'icône du bouton Favori
 * 5. btnFavorite.setOnClickListener → viewModel.toggleFavorite(place)
 * 6. btnShare.setOnClickListener → viewModel.buildShareIntent(place)
 * 7. Observer shareIntent → startActivity(intent)
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    // val args: DetailFragmentArgs by navArgs()  // TODO Étudiant 4 : décommenter après config safeArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO Étudiant 4 : viewModel.loadPlaceDetail(args.xid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
