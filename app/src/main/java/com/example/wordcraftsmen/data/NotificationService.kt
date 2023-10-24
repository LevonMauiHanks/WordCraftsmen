package com.example.wordcraftsmen.data

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import com.example.wordcraftsmen.MainActivity
import com.example.wordcraftsmen.R
import com.example.wordcraftsmen.navigation.Navigation
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {
    fun showBasicNotification() {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        context.run {
            NotificationCompat.Builder(this, getString(R.string.chanel1))
                .setContentTitle(getString(R.string.chanel1_title))
                .setContentText(getString(R.string.chanel1_desc))
                .setSmallIcon(R.drawable.earth_icon)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .setVibrate(longArrayOf(500))
                .setSound(DEFAULT_NOTIFICATION_URI)
                .setContentIntent(pendingIntent)
                .build().let {
                    getSystemService(NotificationManager::class.java).notify(
                        Random.nextInt(),
                        it
                    )
                }
        }
    }
}