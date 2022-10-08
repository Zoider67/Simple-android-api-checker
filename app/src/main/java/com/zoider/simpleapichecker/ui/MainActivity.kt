package com.zoider.simpleapichecker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        val INTENT_EXTRA_KEY_URL = "url"
        val INTENT_EXTRA_KEY_TIME = "time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ApiCheckerApp()
        }
    }
}