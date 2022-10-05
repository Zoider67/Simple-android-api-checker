package com.zoider.simpleapichecker.domain

import android.util.Log
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.apichecker.ApiCheckResult
import com.zoider.simpleapichecker.apichecker.ApiState
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteRequestUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val onResult: (result: ApiCheckResult) -> Unit = {}
) {

    suspend operator fun invoke(httpRequest: HttpRequest) = withContext(defaultDispatcher) {
        Log.d("ExecuteRequestUseCase", "invoke http request on ${httpRequest.url}")
        CheckerHttpClient().executeRequest(httpRequest) { isSuccesful, body ->
            onResult(ApiCheckResult(if (isSuccesful) ApiState.SUCCESS else ApiState.ERROR, body))
        }
    }
}