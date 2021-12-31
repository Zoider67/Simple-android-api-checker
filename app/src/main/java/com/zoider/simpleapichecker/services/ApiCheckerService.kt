package com.zoider.simpleapichecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.zoider.simpleapichecker.activities.MainActivity
import com.zoider.simpleapichecker.helpers.ApiStateChecker
import com.zoider.simpleapichecker.notifications.NotificationHelper

class ApiCheckerService : Service() {

    private lateinit var notificationHelper: NotificationHelper
    private lateinit var apiStateChecker: ApiStateChecker

    override fun onCreate() {
        notificationHelper = NotificationHelper(this)
        apiStateChecker = ApiStateChecker(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, notificationHelper.getDefaultNotification())
        Log.d("ApiCheckerService: ", "onStartCommand()")

        if (intent != null) {
            val url: String? = intent.getStringExtra(MainActivity.INTENT_EXTRA_KEY_URL)
            val time: Long = intent.getLongExtra(MainActivity.INTENT_EXTRA_KEY_TIME, 30)
            Log.d("ApiCheckerService: ", "getting url from activity: $url")
            if (url != null && url.isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Start checking each $time minutes on $url",
                    Toast.LENGTH_SHORT
                ).show()
                apiStateChecker.startCheck(url, time) {
                    notificationHelper.showApiStateNotification(it)
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        apiStateChecker.cleanScope()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}