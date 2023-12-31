package com.example.wordcraftsmen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.wordcraftsmen.navigation.Navigation
import com.example.wordcraftsmen.ui.theme.WordCraftsmenTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.wordcraftsmen.data.NotificationWorker
import java.util.Locale
import java.util.concurrent.TimeUnit

lateinit var textToSpeech: TextToSpeech

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordCraftsmenTheme {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val postNotificationPermission =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                    LaunchedEffect(key1 = true) {
                        if (!postNotificationPermission.status.isGranted) {
                            postNotificationPermission.launchPermissionRequest()
                        }
                    }
                }
                setupRecurringWork()
                setupSpeech(applicationContext)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    Navigation(navHostController)
                }
            }
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(true)
            .build()

        val repeatingRequestSyncWorker =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .addTag(NotificationWorker.WORK_NAME)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            NotificationWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequestSyncWorker
        )
    }

    private fun setupSpeech(context: Context) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.ENGLISH
            }
        }
    }
}