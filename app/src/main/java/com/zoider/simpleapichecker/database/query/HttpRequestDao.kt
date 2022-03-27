package com.zoider.simpleapichecker.database.query

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HttpRequestDao {
    @Insert
    suspend fun insert(request: HttpRequest): Long

    @Delete
    fun delete(request: HttpRequest)

    @Query("SELECT * FROM HttpRequest")
    fun getAll(): Flow<List<HttpRequest>>
}