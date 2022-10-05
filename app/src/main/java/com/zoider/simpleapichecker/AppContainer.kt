package com.zoider.simpleapichecker

import android.content.Context
import androidx.room.Room
import com.zoider.simpleapichecker.database.ApiCheckerDatabase
import com.zoider.simpleapichecker.database.HttpMethodConverter
import com.zoider.simpleapichecker.database.request.BaseRepository


/**
 * Dependency Injection container at the application level.
 * TODO: Redo it via DI library (Dagger 2)
 */

object AppContainer {

    lateinit var database: ApiCheckerDatabase
        private set

    val BASE_REPOSITORY: BaseRepository by lazy {
        BaseRepository(database.httpQueryDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, ApiCheckerDatabase::class.java, "data.db")
            .addTypeConverter(HttpMethodConverter())
            .build()
    }
}