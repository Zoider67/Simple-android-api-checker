package com.zoider.simpleapichecker.schedulers

import android.util.Log
import com.zoider.simpleapichecker.api.ApiState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


class IntervalScheduler() {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    //TODO: make http task class that are nested from interface, add here callback (or to task interface)?
    fun start(url: String, interval: Long, task: IIntervalTask  ) {
        scope.launch {
            val flow = getFlow(url, interval)
            flow.collect { value -> onResponse(value) }
        }
    }

    private fun getFlow(url: String, time: Long): Flow<ApiState> = flow {
        Log.d("ApiChecker: ", "start flow checking on $url")
        while (true) {
            delay(time)
            emit(check(url))
        }
    }


    fun cleanScope() {
        scope.cancel()
    }
}