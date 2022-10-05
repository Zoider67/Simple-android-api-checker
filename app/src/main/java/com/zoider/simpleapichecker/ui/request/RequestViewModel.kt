package com.zoider.simpleapichecker.ui.request

import androidx.lifecycle.*
import com.zoider.simpleapichecker.ExceptionHandler
import com.zoider.simpleapichecker.database.request.BaseRepository
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.domain.ExecuteRequestUseCase
import kotlinx.coroutines.launch

class RequestViewModel(
    private val baseRepository: BaseRepository,
    private val executeRequestUseCase: ExecuteRequestUseCase
) : ViewModel() {

    val httpRequests: LiveData<List<HttpRequest>> = baseRepository.allHttpRequests
    val selectedHttpRequest = MutableLiveData<HttpRequest>()

    fun create(request: HttpRequest) = viewModelScope.launch {
        baseRepository.createRequest(request)
    }

    fun executeRequest(httpRequest: HttpRequest) =
        viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
            executeRequestUseCase(httpRequest)
        }

    fun select(httpRequest: HttpRequest) {
        selectedHttpRequest.postValue(httpRequest)
    }

    fun delete(httpRequest: HttpRequest) = viewModelScope.launch {
        baseRepository.deleteRequest(httpRequest)
    }
}