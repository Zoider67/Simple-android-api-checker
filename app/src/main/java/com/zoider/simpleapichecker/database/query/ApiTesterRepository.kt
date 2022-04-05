package com.zoider.simpleapichecker.database.query

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ApiTesterRepository(private val requestDao: HttpRequestDao) {

    val allHttpRequests: Flow<List<HttpRequest>> = requestDao.getAll()

    @WorkerThread
    suspend fun createRequest(httpRequest: HttpRequest): Long{
        return requestDao.insert(httpRequest)
    }

    @WorkerThread
    suspend fun getAllHttpRequests(): List<HttpRequest>{
        return requestDao.getAll().first()
    }

    @WorkerThread
    suspend fun getRequestById(id: Int): HttpRequest{
        return requestDao.getById(id).first()
    }
}