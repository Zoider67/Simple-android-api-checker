package com.zoider.simpleapichecker.database.query

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HttpQueryDao {
    @Insert
    suspend fun insert(query: HttpQuery): Long

    @Delete
    fun delete(query: HttpQuery)

    @Query("SELECT * FROM HttpQuery")
    fun getAll(): Flow<List<HttpQuery>>
}