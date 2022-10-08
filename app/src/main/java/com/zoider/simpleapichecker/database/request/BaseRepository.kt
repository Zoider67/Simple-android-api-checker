package com.zoider.simpleapichecker.database.request

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import javax.inject.Inject

class BaseRepository @Inject constructor(private val requestDao: HttpRequestDao) {

    val allHttpRequests: LiveData<List<HttpRequest>> = requestDao.getAll()

    @WorkerThread
    suspend fun createRequest(httpRequest: HttpRequest): Long{
        return requestDao.insert(httpRequest)
    }

    @WorkerThread
    suspend fun getAllHttpRequests(): List<HttpRequest>? {
        return requestDao.getAll().value
    }

    @WorkerThread
    suspend fun getRequestById(id: Int): HttpRequest? {
        return requestDao.getById(id).value
    }

    @WorkerThread
    suspend fun deleteRequest(request: HttpRequest){
        requestDao.delete(request)
    }
}