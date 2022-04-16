package com.zoider.simpleapichecker.database

import androidx.room.*
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import com.zoider.simpleapichecker.database.request.HttpRequestDao

@Database(entities = [HttpRequestEntity::class], version = 1)
@TypeConverters(HttpMethodConverter::class)
abstract class ApiCheckerDatabase : RoomDatabase() {
    abstract fun httpQueryDao(): HttpRequestDao
}