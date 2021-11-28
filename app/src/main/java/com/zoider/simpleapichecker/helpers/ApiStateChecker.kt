package com.zoider.simpleapichecker.helpers

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class ApiStateChecker {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private val httpClient = HttpClient(CIO)

    fun startCheck(url: String) {
        scope.launch {
            Log.d("ApiChecker: ", "start checking on $url")
            while (true) {
                Log.d("ApiChecker: ", "sending request...")
                try {
                    val httpResponse: HttpResponse = httpClient.get(url)
                    val body: String = httpResponse.receive()
                    val status = httpResponse.status
                    Log.d("ApiChecker: ", "http response status is $status")
                    Log.d("ApiChecker: ", "http response body is $body")
                    //TODO: handle cases for cases: network problems, server is not available, status is not 200-299
                } catch (c: ResponseException) {
                    Log.e("ApiStateChecker err: ", c.toString())
                } catch (t: Throwable) {
                    Log.e("ApiStateChecker err: ", t.toString())
                }
                delay(TimeUnit.SECONDS.toMillis(20))
            }
        }
    }

    fun cleanScope() {
        scope.cancel()
    }
}