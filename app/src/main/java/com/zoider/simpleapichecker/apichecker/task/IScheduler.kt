package com.zoider.simpleapichecker.apichecker.task

import java.time.Duration

interface IScheduler {

    fun enqueue(task: ITask, timeout: Duration, periodically: Boolean)

    fun getTasks(): ITask
}