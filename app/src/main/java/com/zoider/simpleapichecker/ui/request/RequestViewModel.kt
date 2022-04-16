package com.zoider.simpleapichecker.ui.request

import androidx.lifecycle.*
import com.zoider.simpleapichecker.ExceptionHandler
import com.zoider.simpleapichecker.database.request.ApiTesterRepository
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import com.zoider.simpleapichecker.domain.ExecuteRequestUseCase
import kotlinx.coroutines.launch

class RequestViewModel(
    private val apiTesterRepository: ApiTesterRepository,
    private val executeRequestUseCase: ExecuteRequestUseCase
) : ViewModel() {

    val httpRequestsEntity: LiveData<List<HttpRequestEntity>> = apiTesterRepository.allHttpRequestsEntity.asLiveData()
    val selectedHttpRequest = MutableLiveData<HttpRequestEntity>()

    fun create(requestEntity: HttpRequestEntity) = viewModelScope.launch {
        apiTesterRepository.createRequest(requestEntity)
    }

    fun executeRequest(httpRequestEntity: HttpRequestEntity) =
        viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
            executeRequestUseCase(httpRequestEntity.url, httpRequestEntity.method) {
                //TODO: show notification
            }
        }

    fun select(id: Int) = viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
        selectedHttpRequest.value = apiTesterRepository.getRequestById(id)
    }
}