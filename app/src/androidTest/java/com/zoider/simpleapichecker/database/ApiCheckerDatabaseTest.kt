package com.zoider.simpleapichecker.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zoider.simpleapichecker.database.request.HttpRequestEntity
import com.zoider.simpleapichecker.database.request.HttpRequestDao
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
    private lateinit var db: ApiCheckerDatabase

    @Before
    fun createDb() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ApiCheckerDatabase::class.java)
            .addTypeConverter(HttpMethodConverter())
            .build()
        httpRequestDao = db.httpQueryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun httpQueriesDaoTest() = runBlocking {
        val query1 = HttpRequestEntity(method = HttpMethod.GET, url = "test1")
        val id1 = httpRequestDao.insert(query1)
        Log.d("insertHttpQuery", "id of inserted http query: $id1")
        val query2 = HttpRequestEntity(method = HttpMethod.GET, url = "test2")
        val id2 = httpRequestDao.insert(query2)
        Log.d("insertHttpQuery", "id of inserted http query: $id2")
        var queries = httpRequestDao.getAll().first()
        assert(queries.size == 2)
        queries.forEach { httpRequestDao.delete(it) }
        queries = httpRequestDao.getAll().first()
        assert(queries.isEmpty())
    }
}