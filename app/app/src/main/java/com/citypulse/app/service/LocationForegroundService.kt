package com.citypulse.app.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * TODO Étudiant 2 : Implémenter le ForegroundService de suivi de position.
 *
 * Étapes à suivre :
 * 1. Créer la notification persistante avec NotificationCompat.Builder
 *    (utiliser Constants.LOCATION_CHANNEL_ID et Constants.LOCATION_NOTIFICATION_ID)
 * 2. Appeler startForeground(id, notification) dans onStartCommand
 * 3. Initialiser FusedLocationProviderClient
 * 4. Créer un LocationRequest (interval = Constants.LOCATION_UPDATE_INTERVAL_MS)
 * 5. Démarrer requestLocationUpdates() avec un LocationCallback
 * 6. Diffuser les mises à jour (ex: via LiveData dans un companion object ou BroadcastReceiver)
 * 7. Arrêter proprement les updates dans onDestroy()
 */
class LocationForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO Étudiant 2 : Démarrer la notification et le tracking GPS
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO Étudiant 2 : Arrêter les mises à jour de position
    }
}
