package com.sergio.gistapp.gist.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sergio.gistapp.gist.repository.GistRepository
import com.sergio.gistapp.gist.model.Gist
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform


class GistListViewModel(private val gistRepository: GistRepository) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Gist>>? = null

    fun getAll(queryName: String): Flow<PagingData<Gist>> {
        val lastResult = currentSearchResult
        if (queryName == currentQueryValue && lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Gist>> =
            gistRepository.getAllStream().cachedIn(viewModelScope)

        currentSearchResult = newResult
        return newResult
    }

    fun favoriteGist(gist: Gist) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if(gist.favorite) {
                    gistRepository.insertGist(gist)
                } else {
                    gistRepository.deleteGist(gist)
                }
            }
        }
    }
}