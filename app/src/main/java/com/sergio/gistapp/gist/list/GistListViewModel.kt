package com.sergio.gistapp.gist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sergio.gistapp.gist.repository.GistRepository
import com.sergio.gistapp.gist.model.Gist
import kotlinx.coroutines.flow.Flow


class GistListViewModel(private val gistRepository: GistRepository) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Gist>>? = null

    fun getAll(queryName: String): Flow<PagingData<Gist>> {
        val lastResult = currentSearchResult
        if (queryName == currentQueryValue && lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Gist>> = gistRepository.getAllStream().cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}