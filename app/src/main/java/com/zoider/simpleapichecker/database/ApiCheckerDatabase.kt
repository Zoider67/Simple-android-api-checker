package com.zoider.simpleapichecker.database

import androidx.room.*
import com.zoider.simpleapichecker.database.query.HttpRequest
import com.zoider.simpleapichecker.database.query.HttpRequestDao

@Database(entities = [HttpRequest::class], version = 1)
@TypeConverters(HttpMethodConverter::class)
abstract class ApiCheckerDatabase : RoomDatabase() {
    abstract fun httpQueryDao(): HttpRequestDao
}