package com.zoider.simpleapichecker.domain

import android.util.Log
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.apichecker.ApiCheckResult
import com.zoider.simpleapichecker.apichecker.HttpRequest
import com.zoider.simpleapichecker.commons.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteRequestUseCase(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(url: String, method: HttpMethod, onResult: (result: ApiCheckResult) -> Unit) = withContext(defaultDispatcher) {
        Log.d("ExecuteRequestUseCase", "invoke http request on ${url}")
        val httpRequest = HttpRequest(CheckerHttpClient(), method, url)
        val result = httpRequest.execute()
        onResult(result)
    }
}