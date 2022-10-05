package com.zoider.simpleapichecker.apichecker

import com.zoider.simpleapichecker.database.request.HttpRequest

interface IHttpClient {
    suspend fun executeRequest(httpRequest: HttpRequest, onResult: (isSuccesful: Boolean, body: String) -> Unit)
}