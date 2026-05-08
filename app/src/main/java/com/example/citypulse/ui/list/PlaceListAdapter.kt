package com.example.citypulse.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.citypulse.R
import com.example.citypulse.databinding.ItemPlaceCardBinding
import com.example.citypulse.model.Place

/**
 * Adapter pour la liste des lieux avec gestion efficace des mises à jour via DiffUtil.
 */
class PlaceListAdapter(
    private val onPlaceClick: (Place) -> Unit,
    private val onFavoriteClick: (Place) -> Unit
) : ListAdapter<Place, PlaceListAdapter.PlaceViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlaceViewHolder(private val binding: ItemPlaceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.apply {
                tvPlaceName.text = place.name
                tvPlaceCategory.text = place.category
                // Distance en dur pour l'exemple, sera calculée dynamiquement plus tard
                tvDistance.text = "450 m" 

                // Gestion de l'icône favori
                val favoriteIcon = if (place.isFavorite) {
                    android.R.drawable.btn_star_big_on
                } else {
                    android.R.drawable.btn_star_big_off
                }
                ivFavorite.setImageResource(favoriteIcon)

                root.setOnClickListener { onPlaceClick(place) }
                ivFavorite.setOnClickListener { onFavoriteClick(place) }
                
                // Ici on pourrait charger l'image avec Glide/Coil si photoUrl n'est pas nul
            }
        }
    }

    class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }
}
