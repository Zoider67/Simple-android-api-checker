package com.zoider.simpleapichecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.zoider.simpleapichecker.views.activities.MainActivity
import com.zoider.simpleapichecker.schedulers.IntervalScheduler
import com.zoider.simpleapichecker.notifications.NotificationHelper

class ApiCheckerService : Service() {

    private lateinit var notificationHelper: NotificationHelper
    private lateinit var intervalScheduler: IntervalScheduler

    override fun onCreate() {
        notificationHelper = NotificationHelper(this)
        intervalScheduler = IntervalScheduler(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, notificationHelper.getApiStatusNotification())
        Log.d("ApiCheckerService: ", "onStartCommand()")

        if (intent != null) {
            val url: String? = intent.getStringExtra(MainActivity.INTENT_EXTRA_KEY_URL)
            val time: Long = intent.getLongExtra(MainActivity.INTENT_EXTRA_KEY_TIME, 30)
            Log.d("ApiCheckerService: ", "getting url from activity: $url")
            if (url != null && url.isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Start checking each $time milliseconds on $url",
                    Toast.LENGTH_SHORT
                ).show()
                intervalScheduler.start(url, time) {
                    notificationHelper.showApiStateNotification(it)
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        intervalScheduler.cleanScope()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}