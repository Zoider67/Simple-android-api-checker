package com.zoider.simpleapichecker.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import com.zoider.simpleapichecker.R
import com.zoider.simpleapichecker.services.ApiCheckerService
import java.time.Duration
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_EXTRA_KEY_URL = "url"
        val INTENT_EXTRA_KEY_TIME = "time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startServiceButton = findViewById<Button>(R.id.start_service_button)
        val stopServiceButton = findViewById<Button>(R.id.stop_service_button)
        val urlEditText = findViewById<EditText>(R.id.url_edit_text)

        val hoursPicker: NumberPicker = findViewById(R.id.number_picker_hours)
        val minutesPicker: NumberPicker = findViewById(R.id.number_picker_minutes)
        val secondsPicker: NumberPicker = findViewById(R.id.number_picker_seconds)

        hoursPicker.maxValue = 24
        minutesPicker.maxValue = 60
        secondsPicker.minValue = 1
        secondsPicker.maxValue = 60

        minutesPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        secondsPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        startServiceButton.setOnClickListener {
            val intent = Intent(this, ApiCheckerService::class.java)
            intent.putExtra(INTENT_EXTRA_KEY_URL, urlEditText.text.toString())
            intent.putExtra(
                INTENT_EXTRA_KEY_TIME,
                Duration.parse("PT${hoursPicker.value}H${minutesPicker.value}M${secondsPicker.value}S")
                    .toMillis()
            )
            val res = applicationContext.startForegroundService(intent)
            res.let { Log.d(this.packageName, "starting service: $it") }
        }

        stopServiceButton.setOnClickListener {
            val intent = Intent(this, ApiCheckerService::class.java)
            applicationContext.stopService(intent)
        }
    }
}