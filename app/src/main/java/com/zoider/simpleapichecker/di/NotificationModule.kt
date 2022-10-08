package com.zoider.simpleapichecker.di

import android.content.Context
import com.zoider.simpleapichecker.notifications.NotificationCenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationCenter(@ApplicationContext context: Context): NotificationCenter {
        return NotificationCenter(context)
    }
}