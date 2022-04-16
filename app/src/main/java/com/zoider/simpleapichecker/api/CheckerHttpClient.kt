package com.zoider.simpleapichecker.api

import android.util.Log
import com.zoider.simpleapichecker.apichecker.HttpRequest
import com.zoider.simpleapichecker.apichecker.IHttpClient
import okhttp3.*
import ru.gildor.coroutines.okhttp.await

class CheckerHttpClient: IHttpClient {

    private val client = OkHttpClient()

    private fun buildRequest(httpRequest: HttpRequest): Request {
        val request = Request.Builder()
            .url(httpRequest.url)
            .method(httpRequest.method.name, null)
            .build()
        return request
    }

    override suspend fun executeRequest(httpRequest: HttpRequest, onResult: (isSuccesful: Boolean, body: String) -> Unit) {
        val request = buildRequest(httpRequest)
        val response = client.newCall(request).await()
        Log.d("ExecuteRequestUseCase", "http response ${response.body.toString()}")
        onResult(response.isSuccessful, response.body.toString())
    }
}