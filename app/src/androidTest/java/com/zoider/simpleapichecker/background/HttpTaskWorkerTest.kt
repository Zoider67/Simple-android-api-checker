package com.zoider.simpleapichecker.background

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.workDataOf
import com.zoider.simpleapichecker.ApiCheckerApplication
import com.zoider.simpleapichecker.background.HttpTaskWorker.Companion.HTTP_REQUEST_ID
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@CustomTestApplication(ApiCheckerApplication::class)
interface HiltTestApplication

//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HttpTaskWorkerTest {
    @Inject
    private lateinit var workerFactory: HiltWorkerFactory

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

//    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val context: Context = hilt


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testHttpTaskWorker() {
        val worker = TestListenableWorkerBuilder<HttpTaskWorker>(
            context,
            inputData = workDataOf(HTTP_REQUEST_ID to 0)
        ).setWorkerFactory(workerFactory)
            .build()
        runBlocking {
            val result = worker.doWork()
        }
    }
}