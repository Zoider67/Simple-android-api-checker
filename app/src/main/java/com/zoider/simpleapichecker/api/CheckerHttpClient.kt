package com.zoider.simpleapichecker.api

import android.util.Log
import com.zoider.simpleapichecker.database.HttpMethod
import com.zoider.simpleapichecker.database.query.HttpRequest
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import ru.gildor.coroutines.okhttp.await

class CheckerHttpClient() {

    private val client = OkHttpClient()

    private fun buildRequest(httpRequest: HttpRequest): Request {
        val request = Request.Builder()
            .url(httpRequest.url)
            .method(httpRequest.method.name, null)
            .build()
        return request
    }

    suspend fun executeRequest(httpRequest: HttpRequest): ApiCheckResult {
        val request = buildRequest(httpRequest)
        val response = client.newCall(request).await()
        Log.d("ExecuteRequestUseCase", "http response ${response.body.toString()}")
        return ApiCheckResult(
            if (response.isSuccessful) ApiState.SUCCESS else ApiState.ERROR,
            response.body.toString()
        )
    }
}