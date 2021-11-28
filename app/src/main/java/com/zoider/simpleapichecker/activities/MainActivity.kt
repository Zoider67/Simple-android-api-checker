package com.zoider.simpleapichecker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.services.ApiCheckerService

class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_EXTRA_KEY_URL = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startServiceButton = findViewById<Button>(R.id.start_service_button)
        val urlEditText = findViewById<EditText>(R.id.url_edit_text)

        startServiceButton.setOnClickListener {
            val intent: Intent = Intent(this, ApiCheckerService::class.java)
            intent.putExtra(INTENT_EXTRA_KEY_URL, urlEditText.text.toString())
            val res = applicationContext.startForegroundService(intent)
            res.let { Log.d(this.packageName, "starting service: $it") }
        }
    }
}