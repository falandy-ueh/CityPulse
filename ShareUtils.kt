package com.example.citypulse.ui

import android.content.Context
import android.content.Intent

object ShareUtils {
    // Cette fonction prépare le message avec nom, coordonnées et lien Maps
    fun partagerLieu(context: Context, nom: String, lat: Double, lon: Double) {
        val message = "Regarde ce lieu sur CityPulse !\n\n" +
                "Nom : $nom\n" +
                "Coordonnées : $lat, $lon\n" +
                "Lien Maps : https://www.google.com/maps/search/?api=1&query=$lat,$lon"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND // Intent implicite pour le partage
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        // Affiche le menu de sélection d'application (SMS, Email, WhatsApp...)
        val shareIntent = Intent.createChooser(sendIntent, "Partager via")
        context.startActivity(shareIntent)
    }
}