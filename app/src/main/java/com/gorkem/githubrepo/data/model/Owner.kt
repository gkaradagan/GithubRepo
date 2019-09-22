package com.gorkem.githubrepo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val avatar_url: String,
    val id: Int,
    val login: String
) : Parcelable