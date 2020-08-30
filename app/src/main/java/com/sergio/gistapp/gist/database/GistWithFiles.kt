package com.sergio.gistapp.gist.database

import androidx.room.Embedded
import androidx.room.Relation
import com.sergio.gistapp.gist.model.File
import com.sergio.gistapp.gist.model.Gist

data class GistWithFiles(
    @Embedded val gist: GistEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "gist_id"
    )
    val files: List<FileEntity>
)

fun GistWithFiles.toModel() =  Gist(
        this.gist.id,
        this.gist.login,
        this.gist.avatarUrl,
        this.gist.description,
        files.map { File(it.filename, it.type) })

