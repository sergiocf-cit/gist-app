package com.sergio.gistapp.gist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sergio.gistapp.gist.database.FileDao
import com.sergio.gistapp.gist.database.GistDao
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.model.toEntity
import kotlinx.coroutines.flow.Flow

class GistRepository(
    private val gistApiService: GistApiService,
    private val gistDao: GistDao,
    private val fileDao: FileDao
) {

    fun getAllStream(): Flow<PagingData<Gist>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { GistPagingSource(gistApiService, gistDao) }
        ).flow
    }

    fun insertGist(gist: Gist) {
        gistDao.insert(gist.toEntity())

        for( file in gist.files) {
            fileDao.insert(file.toEntity(gist.id))
        }
    }

    fun deleteGist(gist: Gist) {
        gistDao.delete(gist.toEntity())
        fileDao.deleteAllFiles(gist.id)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

}