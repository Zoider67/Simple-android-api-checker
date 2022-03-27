package com.zoider.simpleapichecker.ui.query

import android.util.Log
import androidx.lifecycle.*
import com.zoider.simpleapichecker.ExceptionHandler
import com.zoider.simpleapichecker.database.query.ApiTesterRepository
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.domain.ExecuteRequestUseCase
import kotlinx.coroutines.launch

class RequestViewModel(
    private val apiTesterRepository: ApiTesterRepository,
    private val executeRequestUseCase: ExecuteRequestUseCase
) : ViewModel() {

    val httpRequests: LiveData<List<HttpRequest>> = apiTesterRepository.allHttpRequests.asLiveData()

    fun create(request: HttpRequest) = viewModelScope.launch {
        apiTesterRepository.createRequest(request)
    }

    fun executeRequest(httpRequest: HttpRequest) =
        viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
            executeRequestUseCase(httpRequest)
        }
}