package com.zoider.simpleapichecker.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import com.zoider.simpleapichecker.helpers.ApiState

class NotificationHelper(val context: Context) {

    private val DEFAULT_CHANNEL_ID = "0"
    private val ERROR_CHANNEL_ID = "1"

    private val API_STATE_NOTIFICATION_ID = 1

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

    fun getDefaultNotification(): Notification {
        return ApiStatusNotification(context, DEFAULT_CHANNEL_ID).build(ApiState.ONLINE)
    }

    fun showApiStateNotification(state: ApiState) {
        val notification = ApiStatusNotification(context, DEFAULT_CHANNEL_ID).build(state)
        notificationManager.notify(API_STATE_NOTIFICATION_ID, notification)
    }
}