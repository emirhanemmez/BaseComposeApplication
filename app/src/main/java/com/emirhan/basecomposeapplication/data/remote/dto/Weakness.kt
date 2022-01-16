package com.emirhan.basecomposeapplication.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weakness(
    val type: String,
    val value: String
) : Parcelable