package com.sergio.gistapp.gist.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.repository.GistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GistListViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var gistListViewModel: GistListViewModel

    private val gist  = Gist(
        "id", "login", "avatarUrl, ", "description",
        listOf(), true
    )

    @Mock
    private lateinit var gistRepository: GistRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun shouldCallInsert() = runBlocking {
        Mockito.doNothing().`when`(gistRepository).insertGist(gist)
        gistListViewModel.favoriteGist(gist)
        delay(500)
        Mockito.verify(gistRepository).insertGist(gist)
    }

    @Test
    fun shouldCallDelete() = runBlocking {
        gist.favorite = false
        Mockito.doNothing().`when`(gistRepository).deleteGist(gist)
        gistListViewModel.favoriteGist(gist)
        delay(500)

        Mockito.verify(gistRepository).deleteGist(gist)
    }


    @Test
    fun shouldGetPage ()  = runBlocking {
        val page = PagingData.from(listOf(gist))
        Mockito.`when`(gistRepository.getAllStream("")).thenReturn(flowOf(page) )
        val result = gistListViewModel.getAll("")
        result.collect {
            Assert.assertNotNull(it)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testCoroutineScope.cleanupTestCoroutines()
    }
}