package com.citypulse.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.citypulse.app.utils.Constants

class CityPulseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val locationChannel = NotificationChannel(
                Constants.LOCATION_CHANNEL_ID,
                getString(R.string.channel_location_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notification du service de localisation en arrière-plan"
            }

            val alertChannel = NotificationChannel(
                Constants.ALERT_CHANNEL_ID,
                getString(R.string.channel_alert_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications pour les lieux à proximité"
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(locationChannel)
            manager.createNotificationChannel(alertChannel)
        }
    }
}
