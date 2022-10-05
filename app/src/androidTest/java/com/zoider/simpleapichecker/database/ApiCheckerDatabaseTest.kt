package com.zoider.simpleapichecker.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.asFlow
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zoider.simpleapichecker.commons.HttpMethod
import com.zoider.simpleapichecker.database.request.HttpRequest
import com.zoider.simpleapichecker.database.request.HttpRequestDao
import com.zoider.simpleapichecker.database.task.TaskDao
import com.zoider.simpleapichecker.database.task.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ApiCheckerDatabaseTest {

    private lateinit var httpRequestDao: HttpRequestDao
    private lateinit var taskDao: TaskDao
    private lateinit var db: ApiCheckerDatabase

    @Before
    fun createDb() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ApiCheckerDatabase::class.java)
            .addTypeConverter(HttpMethodConverter())
            .build()
        httpRequestDao = db.httpQueryDao()
        taskDao = db.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun httpQueriesDaoTest() = runBlocking {
        val query1 = HttpRequest(method = HttpMethod.GET, url = "test1")
        val id1 = httpRequestDao.insert(query1)
        Log.d("insertHttpQuery", "id of inserted http query: $id1")
        val query2 = HttpRequest(method = HttpMethod.GET, url = "test2")
        val id2 = httpRequestDao.insert(query2)
        Log.d("insertHttpQuery", "id of inserted http query: $id2")
        var queries = httpRequestDao.getAll().asFlow().first()
        assert(queries.size == 2)
        queries.forEach { httpRequestDao.delete(it) }
        queries = httpRequestDao.getAll().asFlow().first()
        assert(queries.isEmpty())
    }

    @Test
    fun taskDaoTest() = runBlocking {
        val query1 = HttpRequest(method = HttpMethod.GET, url = "test1")
        val id1 = httpRequestDao.insert(query1)
        Log.d("insertHttpQuery", "id of inserted http query: $id1")
        val query2 = HttpRequest(method = HttpMethod.GET, url = "test2")
        val id2 = httpRequestDao.insert(query2)
        Log.d("insertHttpQuery", "id of inserted http query: $id2")
        var queries = httpRequestDao.getAll().asFlow().first()
        queries.forEach {
            taskDao.insert(
                TaskEntity(
                    name = "testTask ${it.url}",
                    httpRequestId = it.id.toString()
                )
            )
        }

        var tasks = taskDao.getAllWithRequest().asFlow().first()
        assert(tasks.size == 2)
        tasks.forEach {
            println("============TASK INFO============")
            println("task id: ${it.task.id}")
            println("task name: ${it.task.name}")
            println("urL: ${it.httpRequest.url}")
            println("method: ${it.httpRequest.method.name}")
        }
    }
}