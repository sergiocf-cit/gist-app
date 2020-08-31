package com.sergio.gistapp.gist.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.repository.GistRepository
import com.sergio.gistapp.gist.util.getOrAwaitValue
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @Mock
    private lateinit var gistRepository: GistRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Test
    fun shouldGetAllFavorites() {
        val gist = Gist(
            "id", "login", "avatarUrl, ", "description",
            listOf()
        )

        Mockito.`when`(gistRepository.getAllFavorites()).thenReturn(MutableLiveData<List<Gist>>(
            listOf(gist)))

        favoriteViewModel = FavoriteViewModel(gistRepository)

        val result = favoriteViewModel.favorites.getOrAwaitValue()

        Assert.assertTrue(result[0] ==gist )
    }
}