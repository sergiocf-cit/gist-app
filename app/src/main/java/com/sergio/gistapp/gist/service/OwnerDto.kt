package com.sergio.gistapp.gist.service

import com.sergio.gistapp.gist.model.Owner

data class OwnerDto(val login: String, val avatar_url: String)

internal fun OwnerDto.toModel() = Owner(
    this.login,
    this.avatar_url

)