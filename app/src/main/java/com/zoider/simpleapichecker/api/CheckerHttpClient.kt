package com.zoider.simpleapichecker.api

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class CheckerHttpClient(
    private val context: Context,
    private val url: String,
    private val skipSSL: Boolean
) {

    private val httpClient: HttpClient = HttpClient(CIO) {
        engine {
            https {
                if (skipSSL) {
                    trustManager = buildSkipSSLTrustManager()
                }
            }
        }
    }

    @SuppressLint("CustomX509TrustManager")
    private fun buildSkipSSLTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(
                p0: Array<out X509Certificate>?,
                p1: String?
            ) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(
                p0: Array<out X509Certificate>?,
                p1: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate>? = null
        }
    }

    suspend fun check(): ApiState {
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

    private fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected == true
    }
}