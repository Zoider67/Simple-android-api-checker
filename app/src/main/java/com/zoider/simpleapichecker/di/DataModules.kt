package com.zoider.simpleapichecker.di

import android.content.Context
import androidx.room.Room
import com.zoider.simpleapichecker.database.ApiCheckerDatabase
import com.zoider.simpleapichecker.database.HttpMethodConverter
import com.zoider.simpleapichecker.database.request.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ApiCheckerDatabase {
        return Room.databaseBuilder(context, ApiCheckerDatabase::class.java, "data.db")
            .addTypeConverter(HttpMethodConverter())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBaseRepository(database: ApiCheckerDatabase): BaseRepository {
        return BaseRepository(database.httpQueryDao())
    }
}