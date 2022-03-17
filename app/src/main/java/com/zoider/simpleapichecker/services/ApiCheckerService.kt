package com.zoider.simpleapichecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.zoider.simpleapichecker.api.ApiCheckTask
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.views.activities.MainActivity
import com.zoider.simpleapichecker.schedulers.IntervalExecutor
import com.zoider.simpleapichecker.notifications.NotificationHelper

class ApiCheckerService : Service() {

    private lateinit var notificationHelper: NotificationHelper
    private lateinit var intervalExecutor: IntervalExecutor

    private val DEFAULT_INTERVAL_1_MIN: Long = 60000

    override fun onCreate() {
        notificationHelper = NotificationHelper(this)
        intervalExecutor = IntervalExecutor()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, notificationHelper.getApiStatusNotification())
        Log.d("ApiCheckerService: ", "onStartCommand()")

        if (intent != null) {
            val url: String? = intent.getStringExtra(MainActivity.INTENT_EXTRA_KEY_URL)
            val time: Long =
                intent.getLongExtra(MainActivity.INTENT_EXTRA_KEY_TIME, DEFAULT_INTERVAL_1_MIN)
            Log.d("ApiCheckerService: ", "get api check params from activity")
            Log.d("ApiCheckerService: ", "url: $url; time: $time")
            val httpClient = buildHttpClient(url, time, true/*TODO*/)
            if (httpClient != null) {
                Toast.makeText(
                    this,
                    "Start checking each $time milliseconds on $url",
                    Toast.LENGTH_SHORT
                ).show()
                val task: ApiCheckTask = ApiCheckTask(httpClient) {
                    notificationHelper.showApiStateNotification(it)
                }
                intervalExecutor.start(task, time)
            }
        }
        return START_STICKY
    }

    private fun buildHttpClient(url: String?, time: Long, skipSSL: Boolean): CheckerHttpClient? {
        return if (url != null && url.isNotEmpty()) {
            CheckerHttpClient(this.applicationContext, url, skipSSL)
        } else {
            null
        }
    }

    override fun onDestroy() {
        intervalExecutor.cleanScope()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}