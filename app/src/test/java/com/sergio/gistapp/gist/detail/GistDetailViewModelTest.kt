package com.sergio.gistapp.gist.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GistDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var gistDetailViewModel: GistDetailViewModel


    @Test
    fun shouldUpdateGist() {
        val gist = Gist(
            "id", "login", "avatarUrl, ", "description",
            listOf()
        )

        gistDetailViewModel.updateGist(gist)

        val result = gistDetailViewModel.gist.getOrAwaitValue()

        Assert.assertTrue(result == gist)
    }

}