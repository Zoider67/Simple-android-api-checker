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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager


class ApiStateChecker(val context: Context) {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private val httpClient = HttpClient(CIO){
        engine {
            https {
                trustManager = object: X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) { }

                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) { }

                    override fun getAcceptedIssuers(): Array<X509Certificate>? = null
                }
            }
        }
    }


    fun startCheck(url: String, time: Long, onResponse: (apiState: ApiState) -> Any) {
        scope.launch {
            val flow = getFlow(url, time)
            flow.collect { value -> onResponse(value) }
        }
    }

    fun getFlow(url: String, time: Long): Flow<ApiState> = flow {
        Log.d("ApiChecker: ", "start flow checking on $url")
        while (true) {
            delay(time)
            emit(check(url))
        }
    }

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

    fun cleanScope() {
        scope.cancel()
    }
}