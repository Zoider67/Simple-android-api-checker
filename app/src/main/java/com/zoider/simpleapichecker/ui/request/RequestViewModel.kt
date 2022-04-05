package com.zoider.simpleapichecker.ui.request

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
    val selectedHttpRequest = MutableLiveData<HttpRequest>()

    fun create(request: HttpRequest) = viewModelScope.launch {
        apiTesterRepository.createRequest(request)
    }

    fun executeRequest(httpRequest: HttpRequest) =
        viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
            executeRequestUseCase(httpRequest)
        }

    fun select(id: Int) = viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
        selectedHttpRequest.value = apiTesterRepository.getRequestById(id)
    }
}