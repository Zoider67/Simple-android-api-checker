package com.zoider.simpleapichecker.apichecker.task

import com.zoider.simpleapichecker.apichecker.ApiCheckResult

interface ITask {

    suspend fun execute()
}