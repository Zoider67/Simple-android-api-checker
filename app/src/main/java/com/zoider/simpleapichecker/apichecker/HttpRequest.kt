package com.zoider.simpleapichecker.apichecker

import com.zoider.simpleapichecker.commons.HttpMethod

class HttpRequest(val client: IHttpClient, val method: HttpMethod, val url: String) {
    suspend fun execute(): ApiCheckResult {
        var result = ApiCheckResult(ApiState.ERROR, null)
        client.executeRequest(this) { isSuccesful, body ->
            val state = if(isSuccesful) ApiState.SUCCESS else ApiState.ERROR
            result = ApiCheckResult(state, body)
        }
        return result
    }
}