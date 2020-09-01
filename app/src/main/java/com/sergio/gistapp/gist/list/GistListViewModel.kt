package com.sergio.gistapp.gist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.repository.GistRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow


class GistListViewModel(private val gistRepository: GistRepository) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentUserValue: String = ""

    private var currentSearchResult: Flow<PagingData<Gist>>? = null

    fun getAll(user: String): Flow<PagingData<Gist>> {
        val lastResult = currentSearchResult
        if (user == currentUserValue && lastResult != null) {
            return lastResult
        }

        currentUserValue = user

        val newResult: Flow<PagingData<Gist>> =
            gistRepository.getAllStream(user).cachedIn(viewModelScope)

        currentSearchResult = newResult
        return newResult
    }

    fun favoriteGist(gist: Gist) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (gist.favorite) {
                    gistRepository.insertGist(gist)
                } else {
                    gistRepository.deleteGist(gist)
                }
            }
        }
    }
}