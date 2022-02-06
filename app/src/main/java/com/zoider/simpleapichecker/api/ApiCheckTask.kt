package com.zoider.simpleapichecker.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.zoider.simpleapichecker.schedulers.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiCheckTask(val httpClient: CheckerHttpClient, val onCheck: (ApiState) -> Unit) : Task {

    override suspend fun execute() {
        val state = httpClient.check()
        onCheck(state)
    }
}