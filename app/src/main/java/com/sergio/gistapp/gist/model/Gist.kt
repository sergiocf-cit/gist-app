package com.sergio.gistapp.gist.shared

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist(val id: String, val owner: Owner, val description: String?, val files: List<File>)
    : Parcelable