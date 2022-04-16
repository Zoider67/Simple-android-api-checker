package com.zoider.simpleapichecker.database.request

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HttpRequestDao {
    @Insert
    suspend fun insert(requestEntity: HttpRequestEntity): Long

    @Delete
    fun delete(requestEntity: HttpRequestEntity)

    @Query("SELECT * FROM HttpRequestEntity")
    fun getAll(): Flow<List<HttpRequestEntity>>

    @Query("SELECT * FROM HttpRequestEntity WHERE id=:id")
    fun getById(id: Int): Flow<HttpRequestEntity>
}