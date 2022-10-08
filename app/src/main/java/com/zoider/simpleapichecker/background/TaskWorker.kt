package com.zoider.simpleapichecker.background

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TaskWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {

    override fun doWork(): Result {
        return Result.success()
    }
}