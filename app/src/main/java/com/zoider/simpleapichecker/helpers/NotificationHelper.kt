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

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(DEFAULT_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun getDefaultNotification(): Notification {
        val pendingIntent: PendingIntent =
            Intent(context, MainActivity::class.java).let { notificationIntent ->
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                PendingIntent.getActivity(
                    context,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        return NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("kek")
            .setContentText("kekW")
            .setContentIntent(pendingIntent)
            .build()
    }
}