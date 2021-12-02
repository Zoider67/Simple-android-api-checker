package com.zoider.simpleapichecker.helpers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.activities.MainActivity

class NotificationHelper(val context: Context) {

    private val DEFAULT_CHANNEL_ID = "0"
    private val ERROR_CHANNEL_ID = "1"

    private val pendingIntent: PendingIntent =
        Intent(context, MainActivity::class.java).let { notificationIntent ->
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }

    private lateinit var notificationManager: NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val defaultChannel = NotificationChannel(
            DEFAULT_CHANNEL_ID,
            "Default ApiChecker",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Default ApiChecker notification channel"
        }
        val errorChannel = NotificationChannel(
            ERROR_CHANNEL_ID,
            "Error ApiChecker",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Error ApiChecker notification channel"
        }
        notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(defaultChannel)
        notificationManager.createNotificationChannel(errorChannel)
    }

    //TODO: redesign notifications to be more informative
    fun getDefaultNotification(): Notification {
        return NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_check_circle_black_36)
            .setContentTitle("online")
            .setContentText("online")
            .setContentIntent(pendingIntent)
            .build()
    }

    fun getNoNetworkNotificaiton(): Notification {
        return NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_help_black_36)
            .setContentTitle("offline")
            .setContentText("offline")
            .setContentIntent(pendingIntent)
            .build()
    }

    fun getErrorNotification(): Notification {
        return NotificationCompat.Builder(context, ERROR_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_error_36)
            .setContentTitle("error")
            .setContentText("error")
            .setContentIntent(pendingIntent)
            .build()
    }

    fun sendDefaultNotification() {
        notificationManager.notify(1, getDefaultNotification())
    }

    fun sendNoNetworkNotification(){
        notificationManager.notify(2, getNoNetworkNotificaiton())
    }

    fun sendErrorNotification() {
        notificationManager.notify(3, getErrorNotification())
    }
}