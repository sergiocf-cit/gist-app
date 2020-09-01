package com.sergio.gistapp.gist.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistApiService {

    @GET("gists/public")
    suspend fun getAll(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<GistDto>

    @GET(" /users/{user}/gists")
    suspend fun getByUser(
        @Path("user") user: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<GistDto>


}