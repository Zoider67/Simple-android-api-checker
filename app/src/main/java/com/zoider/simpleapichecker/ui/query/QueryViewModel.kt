package com.zoider.simpleapichecker.ui.query

import androidx.lifecycle.*
import com.zoider.simpleapichecker.database.query.ApiTesterRepository
import com.zoider.simpleapichecker.database.query.HttpQuery
import kotlinx.coroutines.launch

class QueryViewModel(private val apiTesterRepository: ApiTesterRepository) : ViewModel() {

    val httpQueries: LiveData<List<HttpQuery>> = apiTesterRepository.allHttpQueries.asLiveData()

    fun create(query: HttpQuery) = viewModelScope.launch {
        apiTesterRepository.createQuery(query)
    }
}