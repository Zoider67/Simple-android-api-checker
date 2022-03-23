package com.zoider.simpleapichecker.database.query

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ApiTesterRepository(private val queryDao: HttpQueryDao) {

    val allHttpQueries: Flow<List<HttpQuery>> = queryDao.getAll()

    @WorkerThread
    suspend fun createQuery(httpQuery: HttpQuery): Long{
        return queryDao.insert(httpQuery)
    }

    @WorkerThread
    suspend fun getAllHttpQueries(): List<HttpQuery>{
        return queryDao.getAll().first()
    }
}