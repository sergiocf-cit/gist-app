package com.sergio.gistapp.gist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class File(val filename:String, val type: String): Parcelable