package com.zoider.simpleapichecker.database

import androidx.room.*
import com.zoider.simpleapichecker.database.query.HttpQuery
import com.zoider.simpleapichecker.database.query.HttpQueryDao

@Database(entities = [HttpQuery::class], version = 1)
@TypeConverters(HttpMethodConverter::class)
abstract class ApiCheckerDatabase : RoomDatabase() {
    abstract fun httpQueryDao(): HttpQueryDao
}