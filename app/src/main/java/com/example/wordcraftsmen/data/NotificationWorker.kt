package com.example.wordcraftsmen.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.wordcraftsmen.R
import java.io.IOException
import javax.inject.Inject

class NotificationWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(
    appContext,
    params
) {

    companion object {
        const val WORK_NAME = "com.example.wordcraftsmen.data.NotificationWorker"
    }

    override suspend fun doWork(): Result {
        try {
            NotificationService(applicationContext).showBasicNotification()
        } catch (e: IOException) {
            return Result.failure(
                workDataOf(
                    applicationContext.getString(R.string.notificationworkererror) to e.localizedMessage
                )
            )
        }

        return Result.success()
    }
}