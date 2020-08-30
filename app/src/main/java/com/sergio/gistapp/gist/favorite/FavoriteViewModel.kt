package com.sergio.gistapp.gist.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.repository.GistRepository
import kotlinx.coroutines.*

class FavoriteViewModel(private val gistRepository: GistRepository): ViewModel() {

    val favorites = gistRepository.getAllFavorites()

}