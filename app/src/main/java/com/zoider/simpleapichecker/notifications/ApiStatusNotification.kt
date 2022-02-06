package com.zoider.simpleapichecker.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.views.activities.MainActivity
import com.zoider.simpleapichecker.api.ApiState
import java.time.LocalDateTime

class ApiStatusNotification(val context: Context, val channelId: String) {

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

    fun build(state: ApiState): Notification {
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(state.resIcon)
            .setColor(state.resColor)
            .setContentTitle(
                "${context.getString(R.string.api_notification_status)} ${context.getString(state.resTitle)}"
            )
            .setContentText(
                "${context.getString(R.string.api_notification_last_update)} ${LocalDateTime.now()}"
            )
            .setContentIntent(pendingIntent)
            .build()
    }
}