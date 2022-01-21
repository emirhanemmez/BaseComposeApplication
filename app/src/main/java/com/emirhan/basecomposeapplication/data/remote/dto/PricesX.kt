package com.emirhan.basecomposeapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PricesX(
    @SerializedName("1stEditionHolofoil") val firstEditionHolofoil: StEditionHolofoil,
    val holofoil: Holofoil,
    val normal: Normal,
    val reverseHolofoil: ReverseHolofoil,
    val unlimitedHolofoil: UnlimitedHolofoil
)