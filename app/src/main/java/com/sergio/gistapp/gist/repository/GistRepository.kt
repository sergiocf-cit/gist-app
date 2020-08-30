package com.sergio.gistapp.gist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.model.Gist
import kotlinx.coroutines.flow.Flow

class GistRepository(private val gistApiService: GistApiService) {

    fun getAllStream(): Flow<PagingData<Gist>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { GistPagingSource(gistApiService) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

}