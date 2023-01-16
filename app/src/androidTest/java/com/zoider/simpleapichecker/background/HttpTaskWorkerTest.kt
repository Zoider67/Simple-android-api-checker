package com.zoider.simpleapichecker.background

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.*
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.zoider.simpleapichecker.background.HttpTaskWorker.Companion.HTTP_REQUEST_ID
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HttpTaskWorkerTest {
    @Inject
    public lateinit var workerFactory: HiltWorkerFactory

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun setUp() {
        hiltRule.inject()

        val config = Configuration.Builder().run {
            setWorkerFactory(workerFactory)
            setMinimumLoggingLevel(Log.DEBUG)
            setExecutor(SynchronousExecutor())
            build()
        }

        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testHttpTaskWorker() {
        val workManager = WorkManager.getInstance(context)
        val request = OneTimeWorkRequestBuilder<HttpTaskWorker>().apply {
//            setInputData(Data.Builder().apply { putString() }.build())
        }.build()
        val workInfo = workManager.enqueue(request).result.get()
        println(workInfo.toString())
    }
}