package com.sergio.gistapp.gist.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.sergio.gistapp.gist.database.*
import com.sergio.gistapp.gist.model.File
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.util.MockitoHelper
import com.sergio.gistapp.gist.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GistRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var gistRepository: GistRepository

    @Mock
    private lateinit var gistApiService: GistApiService

    @Mock
    private lateinit var gistDao: GistDao

    @Mock
    private lateinit var fileDao: FileDao

    @Captor
    private lateinit var insertArgumentCaptor: ArgumentCaptor<FileEntity>

    @Captor
    private lateinit var deleteArgumentCaptor: ArgumentCaptor<String>

    private val gist = Gist(
        "gistId", "login", "avatarUrl", "description",
        listOf(File("filename", "type")), true
    )

    @Test
    fun shouldCallInsertGistAndFileFillingGistId() {
        Mockito.doNothing().`when`(gistDao).insert(any())
        Mockito.doNothing().`when`(fileDao).insert(any())

        gistRepository.insertGist(gist)

        Mockito.verify(gistDao).insert(any())
        Mockito.verify(fileDao).insert(MockitoHelper.capture(insertArgumentCaptor))

        Assert.assertTrue("gistId" == insertArgumentCaptor.value.gistId)
    }

    @Test
    fun shouldCallDeleteGistAndFileFillingGistId() {
        Mockito.doNothing().`when`(gistDao).delete(any())
        Mockito.doNothing().`when`(fileDao).deleteAllFiles(any())

        gistRepository.deleteGist(gist)

        Mockito.verify(gistDao).delete(any())
        Mockito.verify(fileDao).deleteAllFiles(MockitoHelper.capture(deleteArgumentCaptor))

        MatcherAssert.assertThat("gistId", Matchers.equalTo(deleteArgumentCaptor.value))
    }

    @Test
    fun shouldGetAllFavorites() {
        val myFavorite = Gist(
            "gistId", "login", "avatarUrl", "description",
            listOf(File("filename", "type"))
        )

        val gistWithFiles = GistWithFiles(
            GistEntity(
                "gistId", "description", "login",
                "avatarUrl"
            ), listOf(FileEntity(0, "gistId", "filename", "type"))
        )

        Mockito.`when`(gistDao.getAllGistsWithFiles())
            .thenReturn(MutableLiveData(listOf(gistWithFiles)))


        val result = gistRepository.getAllFavorites().getOrAwaitValue()

        Assert.assertThat(result[0], CoreMatchers.equalTo(myFavorite))
    }

}