package com.zoider.simpleapichecker.schedulers

import kotlinx.coroutines.*


class IntervalExecutor() {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun start(task: Task, interval: Long) {
        scope.launch {
            while (true) {
                delay(interval)
                task.execute()
            }
        }
    }

    fun cleanScope() {
        scope.cancel()
    }
}