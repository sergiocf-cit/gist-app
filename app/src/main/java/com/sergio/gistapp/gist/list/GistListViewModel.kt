package com.sergio.gistapp.gist.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sergio.gistapp.gist.repository.GistRepository
import com.sergio.gistapp.gist.service.GistDto
import androidx.lifecycle.liveData


class GistListViewModel(private val gistRepository: GistRepository) : ViewModel() {
    val gist: LiveData<List<GistDto>> = liveData {
        emit(gistRepository.getAll())
    }


    fun teste() {
        Log.i("GistListViewModel", "teste")
    }
}