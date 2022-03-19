package com.zoider.simpleapichecker

import android.app.Application

class ApiCheckerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.provide(this)
    }
}