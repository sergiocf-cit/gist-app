package com.sergio.gistapp.gist.model

import android.os.Parcelable
import com.sergio.gistapp.gist.database.FileEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class File(val filename:String, val type: String): Parcelable

fun File.toEntity(gistId: String): FileEntity = FileEntity(
    0,
    gistId,
    this.filename,
    this.type
)