package com.sergio.gistapp.gist.model

import android.os.Parcelable
import com.sergio.gistapp.gist.database.GistEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist(val id: String, val login: String, val avatarUrl: String,  val description: String?, val files: List<File>,
var favorite: Boolean = false)
    : Parcelable


fun Gist.toEntity(): GistEntity = GistEntity(
    this.id,
    this.description,
    this.login,
    this.avatarUrl
)