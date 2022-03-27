package com.zoider.simpleapichecker.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.zoider.simpleapichecker.schedulers.Task

//class ApiCheckTask(val httpClient: CheckerHttpClient, val onCheck: (ApiState) -> Unit) : Task {
//
//    override suspend fun execute() {
//        val state = httpClient.check()
//        onCheck(state)
//    }
//}