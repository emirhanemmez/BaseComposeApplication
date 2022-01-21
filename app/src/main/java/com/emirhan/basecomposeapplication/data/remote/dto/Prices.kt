package com.emirhan.basecomposeapplication.data.remote.dto

data class Prices(
    val averageSellPrice: Double,
    val avg1: Double,
    val avg30: Double,
    val avg7: Double,
    val lowPrice: Double,
    val lowPriceExPlus: Double,
    val reverseHoloAvg1: Double,
    val reverseHoloAvg30: Double,
    val reverseHoloAvg7: Double,
    val reverseHoloLow: Double,
    val reverseHoloSell: Double,
    val reverseHoloTrend: Double,
    val trendPrice: Double
)