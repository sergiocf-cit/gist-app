package com.sergio.gistapp.gist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "file_table")
data class FileEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "gist_id")
    val gistId: String,

    @ColumnInfo(name = "file_name")
    val filename:String,
    val type: String
)