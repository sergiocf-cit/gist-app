package com.sergio.gistapp.gist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.sergio.gistapp.gist.database.FileDao
import com.sergio.gistapp.gist.database.GistDao
import com.sergio.gistapp.gist.database.toModel
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.model.Gist
import com.sergio.gistapp.gist.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GistRepository(
    private val gistApiService: GistApiService,
    private val gistDao: GistDao,
    private val fileDao: FileDao
) {

    fun getAllStream(): Flow<PagingData<Gist>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { GistPagingSource(gistApiService) }
        ).flow.map {
            markFavoriteGists(it)
        }
    }

    fun insertGist(gist: Gist) {
        gistDao.insert(gist.toEntity())

        for (file in gist.files) {
            fileDao.insert(file.toEntity(gist.id))
        }
    }

    fun deleteGist(gist: Gist) {
        gistDao.delete(gist.toEntity())
        fileDao.deleteAllFiles(gist.id)
    }

    fun getAllFavorites() : LiveData<List<Gist>> {
        return Transformations.map(gistDao.getAllGistsWithFiles()) {
            list -> list.map { it.toModel() }
        }
    }

    private suspend fun markFavoriteGists(pagingData: PagingData<Gist>): PagingData<Gist> {
        return withContext(Dispatchers.IO) {
            val favoriteIds = gistDao.getAllGists().map { it.id }

            return@withContext pagingData.map { gist ->
                gist.favorite = favoriteIds.contains(gist.id)
                gist
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

}

