package com.zoider.simpleapichecker.database.request

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HttpRequestDao {
    @Insert
    suspend fun insert(request: HttpRequest): Long

    @Delete
    suspend fun delete(request: HttpRequest)

    @Query("SELECT * FROM HttpRequest")
    fun getAll(): LiveData<List<HttpRequest>>

    @Query("SELECT * FROM HttpRequest WHERE id=:id")
    fun getById(id: Int): LiveData<HttpRequest>
}