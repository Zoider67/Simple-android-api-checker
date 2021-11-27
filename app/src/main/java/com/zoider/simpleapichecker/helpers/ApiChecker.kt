package com.zoider.simpleapichecker.helpers

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class ApiStateChecker {
    enum class ApiState {
        ONLINE, SERVER_ERROR, NOT_CONNECTED
    }

    private fun checkApi(url: String): ApiState {
        var result: ApiState = ApiState.NOT_CONNECTED
        val httpResponse: HttpResponse
        runBlocking {
            httpResponse = HttpClient().get(url)
            result = httpResponse.receive()
        }
        if(httpResponse.status.value in 200 .. 299){
            result = ApiState.ONLINE
        } else {

        }
        return result
    }
}