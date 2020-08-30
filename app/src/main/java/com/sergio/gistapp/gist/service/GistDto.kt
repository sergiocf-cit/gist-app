package com.sergio.gistapp.gist.service

import com.sergio.gistapp.gist.model.Gist

data class GistDto (val id: String, val owner: OwnerDto, val description: String?, val files:DynamicFileDto)

fun GistDto.toModel() = Gist(
    this.id,
    this.owner.login,
    this.owner.avatar_url,
    this.description,
    this.files.toModel()
)

