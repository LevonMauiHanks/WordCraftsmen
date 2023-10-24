package com.example.wordcraftsmen

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WordCraftsmenApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() = Configuration
        .Builder()
        .setWorkerFactory(workerFactory)
        .build()

    override fun onCreate() {
        super.onCreate()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createChannel(
            getString(R.string.chanel1),
            getString(R.string.chanel1_title),
            getString(R.string.chanel1_desc),
            NotificationManager.IMPORTANCE_HIGH,
            notificationManager
        )
    }
}

private fun createChannel(
    id: String,
    title: String,
    descriptionText: String,
    importance: Int,
    notificationManager: NotificationManager
) {
    NotificationChannel(
        id,
        title,
        importance
    ).apply {
        setShowBadge(false)
        enableLights(true)
        lightColor = Color.RED
        enableVibration(true)
        description = descriptionText
    }.let { notificationChannel ->
        notificationManager.createNotificationChannel(notificationChannel)
    }
}