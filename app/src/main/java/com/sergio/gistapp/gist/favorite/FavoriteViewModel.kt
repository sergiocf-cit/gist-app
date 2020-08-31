package com.sergio.gistapp.gist.favorite

import androidx.lifecycle.ViewModel
import com.sergio.gistapp.gist.repository.GistRepository

class FavoriteViewModel(gistRepository: GistRepository): ViewModel() {

    val favorites = gistRepository.getAllFavorites()

}