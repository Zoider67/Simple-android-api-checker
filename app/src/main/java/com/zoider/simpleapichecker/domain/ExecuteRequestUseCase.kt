package com.zoider.simpleapichecker.domain

import android.util.Log
import com.zoider.simpleapichecker.http.CheckerHttpClient
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import com.zoider.simpleapichecker.notifications.NotificationCenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteRequestUseCase(
//    httpRequest: HttpRequest,
    private val notificationCenter: NotificationCenter,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(httpRequestEntity: HttpRequestEntity) = withContext(defaultDispatcher) {
        Log.d("ExecuteRequestUseCase", "invoke http request on ${httpRequestEntity.url}")
        val client = CheckerHttpClient()
        val result = client.executeRequest(httpRequestEntity)
        notificationCenter.showApiStateNotification(result.state)
    }
}