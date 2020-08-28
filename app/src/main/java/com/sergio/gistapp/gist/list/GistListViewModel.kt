package com.sergio.gistapp.gist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sergio.gistapp.gist.repository.GistRepository
import androidx.lifecycle.liveData
import com.sergio.gistapp.gist.shared.Gist

class GistListViewModel(private val gistRepository: GistRepository) : ViewModel() {
    
    val gist: LiveData<List<Gist>> = liveData {
        emit(gistRepository.getAll())
    }
}