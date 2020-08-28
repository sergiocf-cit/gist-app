package com.sergio.gistapp.gist.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GistApiService {

    @GET("gists/public")
    suspend fun getAll(@Query("page") page: Int = 0): List<GistDto>

}