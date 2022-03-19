package com.zoider.simpleapichecker.database.query

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.first
import java.util.concurrent.Flow

class ApiTesterRepository(private val queryDao: HttpQueryDao) {

    @WorkerThread
    suspend fun createQuery(httpQuery: HttpQuery): Long{
        return queryDao.insert(httpQuery)
    }

    @WorkerThread
    suspend fun getAllHttpQueries(): List<HttpQuery>{
        return queryDao.getAll().first()
    }
}