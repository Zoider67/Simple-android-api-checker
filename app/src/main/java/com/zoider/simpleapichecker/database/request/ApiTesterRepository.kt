package com.zoider.simpleapichecker.database.request

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ApiTesterRepository(private val requestDao: HttpRequestDao) {

    val allHttpRequestsEntity: Flow<List<HttpRequestEntity>> = requestDao.getAll()

    @WorkerThread
    suspend fun createRequest(httpRequestEntity: HttpRequestEntity): Long{
        return requestDao.insert(httpRequestEntity)
    }

    @WorkerThread
    suspend fun getAllHttpRequests(): List<HttpRequestEntity>{
        return requestDao.getAll().first()
    }

    @WorkerThread
    suspend fun getRequestById(id: Int): HttpRequestEntity{
        return requestDao.getById(id).first()
    }
}