package com.zoider.simpleapichecker.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.zoider.simpleapichecker.schedulers.IIntervalTask
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class HttpApi: IIntervalTask {

    private val httpClient = HttpClient(CIO)

    private suspend fun check(url: String): ApiState {
        var result: ApiState
        Log.d("ApiChecker: ", "sending request...")
        try {
            result = if (isOnline()) {
                val httpResponse: HttpResponse = httpClient.get(url)
                val body: String = httpResponse.receive()
                val status = httpResponse.status
                Log.d("ApiChecker: ", "http response status is $status")
                Log.d("ApiChecker: ", "http response body is $body")
                ApiState.ONLINE
            } else {
                ApiState.NO_NETWORK
            }
        } catch (c: ResponseException) {
            Log.e("ApiStateChecker err: ", c.toString())
            result = ApiState.SERVER_IS_NOT_AVAILABLE
        } catch (t: Throwable) {
            Log.e("ApiStateChecker err: ", t.toString())
            result = ApiState.SERVER_IS_NOT_AVAILABLE
        }
        return result
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }

    override fun execute() {
        TODO("Not yet implemented")
    }
}