package com.zoider.simpleapichecker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zoider.simpleapichecker.database.request.HttpRequestDao
import com.zoider.simpleapichecker.database.request.HttpRequest

@Database(entities = [HttpRequest::class], version = 2)
@TypeConverters(HttpMethodConverter::class)
abstract class ApiCheckerDatabase : RoomDatabase() {
    abstract fun httpQueryDao(): HttpRequestDao
}