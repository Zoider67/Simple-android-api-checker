package com.zoider.simpleapichecker.ui.request

import android.util.Log
import androidx.lifecycle.*
import com.zoider.simpleapichecker.ExceptionHandler
import com.zoider.simpleapichecker.api.ApiCheckResult
import com.zoider.simpleapichecker.api.ApiState
import com.zoider.simpleapichecker.api.CheckerHttpClient
import com.zoider.simpleapichecker.database.request.BaseRepository
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.notifications.NotificationCenter
import com.zoider.simpleapichecker.ui.consts.UIApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val baseRepository: BaseRepository,
    private val notificationCenter: NotificationCenter
) : ViewModel() {

    val httpRequests: LiveData<List<HttpRequest>> = baseRepository.allHttpRequests
    val selectedHttpRequest = MutableLiveData<HttpRequest?>()

    fun create(request: HttpRequest) = viewModelScope.launch {
        baseRepository.createRequest(request)
    }

    fun executeRequest(httpRequest: HttpRequest) =
        viewModelScope.launch(ExceptionHandler.coroutineExceptionHandler) {
            Log.d("ExecuteRequestUseCase", "invoke http request on ${httpRequest.url}")
            CheckerHttpClient().executeRequest(httpRequest) { isSuccesful, body ->
                val apiState = if (isSuccesful) ApiState.SUCCESS else ApiState.ERROR
                notificationCenter.showApiStateNotification(UIApiState.of(apiState))
            }
        }

    fun get(requestId: String): LiveData<HttpRequest> {
        return baseRepository.getRequestById(requestId.toInt())
    }

    fun delete(httpRequest: HttpRequest) = viewModelScope.launch {
        baseRepository.deleteRequest(httpRequest)
    }
}