package com.sergio.gistapp.gist.database

import androidx.room.Embedded
import androidx.room.Relation

data class GistWithFiles(
    @Embedded val gist: GistEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "gist_id"
    )
    val files: List<FileEntity>
)