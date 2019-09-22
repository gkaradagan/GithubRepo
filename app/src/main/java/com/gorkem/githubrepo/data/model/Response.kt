package com.gorkem.githubrepo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Response : Parcelable {
    var message: String = ""
}