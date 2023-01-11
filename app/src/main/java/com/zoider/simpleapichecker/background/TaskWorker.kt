package com.zoider.simpleapichecker.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker

import androidx.work.WorkerParameters
import com.zoider.simpleapichecker.api.ApiState
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.database.request.BaseRepository
import com.zoider.simpleapichecker.notifications.NotificationCenter
import com.zoider.simpleapichecker.ui.consts.UIApiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class HttpTaskWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val notificationCenter: NotificationCenter,
    private val baseRepository: BaseRepository
) :
    CoroutineWorker(context, workerParameters) {

    companion object {
        const val HTTP_REQUEST_ID = "HTTP_REQUEST_ID"
    }

    override suspend fun doWork(): Result {

        val id = inputData.getInt(HTTP_REQUEST_ID, -1)

        val request = baseRepository.getRequestById(id).value

        if (request != null) {
            CheckerHttpClient().executeRequest(request) { isSuccessful, body ->
                val apiState = if (isSuccessful) ApiState.SUCCESS else ApiState.ERROR
                notificationCenter.showApiStateNotification(UIApiState.of(apiState))
            }
        }

        return Result.success()
    }
}