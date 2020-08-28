package com.sergio.gistapp.gist.repository

import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.service.GistDto

class GistRepository(private val gistApiService: GistApiService) {

    suspend fun getAll(): List<GistDto> {
        return gistApiService.getAll()
    }

}