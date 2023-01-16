package com.zoider.simpleapichecker.background

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
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

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
//    private val context: Context = hilt


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