package com.emirhan.basecomposeapplication.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val large: String,
    val small: String
) : Parcelable