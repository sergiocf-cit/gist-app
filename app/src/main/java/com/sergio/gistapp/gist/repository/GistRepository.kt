package com.sergio.gistapp.gist.repository

import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.service.GistDto
import com.sergio.gistapp.gist.service.toModel
import com.sergio.gistapp.gist.shared.Gist

class GistRepository(private val gistApiService: GistApiService) {

    suspend fun getAll(): List<Gist> {
        return gistApiService.getAll().map { it.toModel() }
    }

}