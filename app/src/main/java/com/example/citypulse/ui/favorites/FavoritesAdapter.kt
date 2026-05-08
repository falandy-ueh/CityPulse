package com.example.citypulse.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.citypulse.databinding.ItemPlaceCardBinding
import com.example.citypulse.model.Place

/**
 * Adapter spécifique pour les favoris affichant le badge "Favori".
 */
class FavoritesAdapter(
    private val onPlaceClick: (Place) -> Unit
) : ListAdapter<Place, FavoritesAdapter.FavoriteViewHolder>(FavoriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemPlaceCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoriteViewHolder(private val binding: ItemPlaceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.apply {
                tvPlaceName.text = place.name
                tvPlaceCategory.text = place.category
                tvDistance.text = "450 m"
                
                // Affichage du badge spécifique aux favoris
                badgeFavorite.visibility = View.VISIBLE
                ivFavorite.setImageResource(android.R.drawable.btn_star_big_on)

                root.setOnClickListener { onPlaceClick(place) }
            }
        }
    }

    class FavoriteDiffCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean = oldItem == newItem
    }
}
