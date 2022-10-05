package com.zoider.simpleapichecker.apichecker.task

import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.database.request.HttpRequest

class HttpRequestTask(val name: String, val request: HttpRequest) : ITask {

    override suspend fun execute() {
        CheckerHttpClient().executeRequest(request) { isSuccessful, responseBody ->
            {

            }
        }
    }
}