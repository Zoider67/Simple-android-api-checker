package com.zoider.simpleapichecker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.services.ApiCheckerService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startServiceButton = findViewById<Button>(R.id.start_service_button)
        startServiceButton.setOnClickListener {
            val intent: Intent = Intent(this, ApiCheckerService::class.java)
            val res = applicationContext.startForegroundService(intent)
            res.let { Log.d(this.packageName, "starting service: $it") }
        }
    }
}