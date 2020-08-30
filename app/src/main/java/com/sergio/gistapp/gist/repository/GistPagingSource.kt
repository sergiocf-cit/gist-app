package com.sergio.gistapp.gist.repository

import androidx.paging.PagingSource
import com.sergio.gistapp.gist.database.GistDao
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.service.toModel
import com.sergio.gistapp.gist.model.Gist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 0

class GistPagingSource(
    private val service: GistApiService,
    private val gistDao: GistDao
) : PagingSource<Int, Gist>() {

    private var job = Job()

    private val scope = CoroutineScope(Dispatchers.IO + job)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gist> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {

            val gist = service.getAll(position, params.loadSize).map { it.toModel() }

            scope.launch {
                val favoriteIds = gistDao.getAllGists().map { it.id }

                //Set Favorite for saved ones
                gist.filter {   favoriteIds.contains(it.id)  }.forEach {
                    it.favorite = true
                }
            }

            LoadResult.Page(
                data = gist,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (gist.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}