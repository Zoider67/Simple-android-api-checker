package com.zoider.simpleapichecker.services

import android.app.*
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import com.zoider.simpleapichecker.activities.MainActivity
import com.zoider.simpleapichecker.helpers.NotificationHelper

class ApiCheckerService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationHelper = NotificationHelper(this)
        startForeground(1, notificationHelper.getDefaultNotification())
        return START_STICKY
    }
}