package com.emirhan.basecomposeapplication.domain.model

import android.os.Parcelable
import com.emirhan.basecomposeapplication.data.remote.dto.Images
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val artistName: String,
    val images: Images
) : Parcelable
