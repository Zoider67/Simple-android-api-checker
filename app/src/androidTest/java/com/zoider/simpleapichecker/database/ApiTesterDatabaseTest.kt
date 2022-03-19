package com.zoider.simpleapichecker.database

import android.content.Context
import android.util.Log
import androidx.room.Query
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.services.storage.internal.TestStorageUtil
import com.zoider.simpleapichecker.database.query.ApiTesterRepository
import com.zoider.simpleapichecker.database.query.HttpQuery
import com.zoider.simpleapichecker.database.query.HttpQueryDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ApiTesterDatabaseTest {

    private lateinit var httpQueryDao: HttpQueryDao
    private lateinit var db: ApiTesterDatabase

    @Before
    fun createDb() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ApiTesterDatabase::class.java)
            .addTypeConverter(HttpMethodConverter())
            .build()
        httpQueryDao = db.httpQueryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun httpQueriesDaoTest() = runBlocking {
        val query1 = HttpQuery(method = HttpMethod.GET, url = "test1")
        val id1 = httpQueryDao.insert(query1)
        Log.d("insertHttpQuery", "id of inserted http query: $id1")
        val query2 = HttpQuery(method = HttpMethod.GET, url = "test2")
        val id2 = httpQueryDao.insert(query2)
        Log.d("insertHttpQuery", "id of inserted http query: $id2")
        var queries = httpQueryDao.getAll().first()
        assert(queries.size == 2)
        queries.forEach { httpQueryDao.delete(it) }
        queries = httpQueryDao.getAll().first()
        assert(queries.isEmpty())
    }
}