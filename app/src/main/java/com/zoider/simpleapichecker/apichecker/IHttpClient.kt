package com.zoider.simpleapichecker.apichecker

interface IHttpClient {
    suspend fun executeRequest(httpRequest: HttpRequest, onResult: (isSuccesful: Boolean, body: String) -> Unit)
}