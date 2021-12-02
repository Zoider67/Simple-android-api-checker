package com.zoider.simpleapichecker.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class ApiStateChecker(val context: Context) {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private val httpClient = HttpClient(CIO)

    fun startCheck(url: String, time: Long, onResponse: (apiState: ApiState) -> Any) {
        scope.launch {
            Log.d("ApiChecker: ", "start checking on $url")
            //TODO: refactor with kotlin flows
            while (true) {
                delay(TimeUnit.MINUTES.toMillis(time))
                Log.d("ApiChecker: ", "sending request...")
                try {
                    if(isOnline()) {
                        val httpResponse: HttpResponse = httpClient.get(url)
                        val body: String = httpResponse.receive()
                        val status = httpResponse.status
                        Log.d("ApiChecker: ", "http response status is $status")
                        Log.d("ApiChecker: ", "http response body is $body")
                        //TODO: show on notification status and data???
                        onResponse(ApiState.ONLINE)
                    } else {
                        onResponse(ApiState.NO_NETWORK)
                    }
                } catch (c: ResponseException) {
                    Log.e("ApiStateChecker err: ", c.toString())
                    onResponse(ApiState.SERVER_IS_NOT_AVAILABLE)
                } catch (t: Throwable) {
                    Log.e("ApiStateChecker err: ", t.toString())
                    onResponse(ApiState.SERVER_IS_NOT_AVAILABLE)
                }
            }
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }

    fun cleanScope() {
        scope.cancel()
    }
}