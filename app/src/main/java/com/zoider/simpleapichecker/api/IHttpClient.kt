package com.zoider.simpleapichecker.api

import com.zoider.simpleapichecker.database.request.HttpRequest

interface IHttpClient {
    suspend fun executeRequest(httpRequest: HttpRequest, onResult: (isSuccesful: Boolean, body: String) -> Unit)
}