package com.sergio.gistapp.gist.shared

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner (val login: String, val avatar_url: String):Parcelable