package com.zoider.simpleapichecker.http

import android.util.Log
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import okhttp3.*
import ru.gildor.coroutines.okhttp.await

class CheckerHttpClient() {

    private val client = OkHttpClient()

    private fun buildRequest(httpRequestEntity: HttpRequestEntity): Request {
        val request = Request.Builder()
            .url(httpRequestEntity.url)
            .method(httpRequestEntity.method.name, null)
            .build()
        return request
    }

    suspend fun executeRequest(httpRequestEntity: HttpRequestEntity): ApiCheckResult {
        val request = buildRequest(httpRequestEntity)
        val response = client.newCall(request).await()
        Log.d("ExecuteRequestUseCase", "http response ${response.body.toString()}")
        return ApiCheckResult(
            if (response.isSuccessful) ApiState.SUCCESS else ApiState.ERROR,
            response.body.toString()
        )
    }
}