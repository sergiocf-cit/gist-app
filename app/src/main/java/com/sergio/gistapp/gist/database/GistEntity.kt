package com.sergio.gistapp.gist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sergio.gistapp.gist.model.Gist

@Entity(tableName = "gist_table")
data class GistEntity(
    @PrimaryKey
    val id: String,
    val description: String?,
    val login: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)

