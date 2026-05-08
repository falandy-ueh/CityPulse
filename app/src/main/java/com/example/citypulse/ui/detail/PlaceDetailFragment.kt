package com.example.citypulse.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.citypulse.databinding.FragmentPlaceDetailBinding
import com.example.citypulse.model.Place
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment affichant les détails complets d'un lieu.
 * Gère les notes personnelles, les favoris et le partage.
 */
class PlaceDetailFragment : Fragment() {

    private var _binding: FragmentPlaceDetailBinding? = null
    private val binding get() = _binding!!

    // Donnée factice pour l'exemple (sera récupérée via SafeArgs)
    private var currentPlace: Place? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        
        // Simulation d'un chargement de données
        loadPlaceData()
    }

    private fun setupUI() {
        // Toolbar : Gestion du bouton retour
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupListeners() {
        // Bouton Favori avec animation Pulse
        binding.ivFavoriteDetail.setOnClickListener {
            animateFavoriteButton()
            toggleFavorite()
        }

        // Bouton Partager (Intent implicite)
        binding.fabShare.setOnClickListener {
            sharePlace()
        }

        // Sauvegarde des notes
        binding.btnSaveNotes.setOnClickListener {
            val note = binding.etNotes.text.toString()
            Snackbar.make(binding.root, "Note sauvegardée !", Snackbar.LENGTH_SHORT).show()
            // Logique ViewModel à venir
        }
    }

    private fun loadPlaceData() {
        // En attendant SafeArgs, on simule des données
        val place = Place(
            "1", "Le Gourmet", 48.8566, 2.3522, 
            "Restaurants", "12 Rue de Rivoli, 75004 Paris", null,
            userNote = "Excellent restaurant, surtout le dessert !"
        )
        currentPlace = place

        binding.apply {
            tvDetailName.text = place.name
            chipDetailCategory.text = place.category
            tvDetailAddress.text = place.address ?: "Adresse non renseignée"
            tvDetailCoords.text = "Lat: ${place.latitude}, Lng: ${place.longitude}"
            etNotes.setText(place.userNote)
            
            // État initial du favori
            val favIcon = if (place.isFavorite) {
                android.R.drawable.btn_star_big_on
            } else {
                android.R.drawable.btn_star_big_off
            }
            ivFavoriteDetail.setImageResource(favIcon)
        }
    }

    private fun animateFavoriteButton() {
        val anim = ScaleAnimation(
            1.0f, 1.3f, 1.0f, 1.3f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 200
            repeatCount = 1
            repeatMode = Animation.REVERSE
        }
        binding.ivFavoriteDetail.startAnimation(anim)
    }

    private fun toggleFavorite() {
        // Logique UI simple pour l'exemple
        Snackbar.make(binding.root, "Favoris mis à jour", Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Partage le lieu via un Intent implicite (Texte + Lien Google Maps)
     */
    private fun sharePlace() {
        currentPlace?.let { place ->
            val shareText = "📍 ${place.name} - ${place.address}\n🗺️ https://maps.google.com/?q=${place.latitude},${place.longitude}"
            
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Partager via")
            startActivity(shareIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
