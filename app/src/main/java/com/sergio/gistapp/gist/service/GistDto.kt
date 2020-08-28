package com.sergio.gistapp.gist.service

data class GistDto (val id: String, val owner: OwnerDto, val description: String, val files:DynamicFileDto)

