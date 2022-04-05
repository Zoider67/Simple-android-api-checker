package com.zoider.simpleapichecker.domain

import android.util.Log
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.notifications.NotificationCenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteRequestUseCase(
//    httpRequest: HttpRequest,
    private val notificationCenter: NotificationCenter,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(httpRequest: HttpRequest) = withContext(defaultDispatcher) {
        Log.d("ExecuteRequestUseCase", "invoke http request on ${httpRequest.url}")
        val client = CheckerHttpClient()
        val result = client.executeRequest(httpRequest)
        notificationCenter.showApiStateNotification(result.state)
    }
}