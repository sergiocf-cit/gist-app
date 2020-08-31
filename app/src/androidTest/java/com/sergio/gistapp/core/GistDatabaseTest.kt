package com.sergio.gistapp.core

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sergio.gistapp.gist.database.FileDao
import com.sergio.gistapp.gist.database.FileEntity
import com.sergio.gistapp.gist.database.GistDao
import com.sergio.gistapp.gist.database.GistEntity
import com.sergio.gistapp.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GistDatabaseTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var gistDao: GistDao
    private lateinit var fileDao: FileDao

    private lateinit var db: GistDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, GistDatabase::class.java).build()
        gistDao = db.gistDao
        fileDao = db.fileDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeGistAndReadInList() {
        val gist = GistEntity("id", "description", "login",
        "avatarUrl")
        gistDao.insert(gist)


        val result = gistDao.getAllGists()
        Assert.assertThat(result[0], CoreMatchers.equalTo(gist))
    }

    @Test
    @Throws(Exception::class)
    fun writeGistWithFilesAndReadInList() {
        val gist = GistEntity("id", "description", "login",
            "avatarUrl")
        gistDao.insert(gist)

        val file = FileEntity(1, "id", "filename", "type")
        fileDao.insert(file)


        val result = gistDao.getAllGistsWithFiles().getOrAwaitValue()
        Assert.assertThat(result[0].gist, CoreMatchers.equalTo(gist))
        Assert.assertThat(result[0].files[0], CoreMatchers.equalTo(file))

    }
}