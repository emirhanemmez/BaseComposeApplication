package com.emirhan.basecomposeapplication.data.remote.dto

data class Set(
    val id: String,
    val images: ImagesX,
    val legalities: LegalitiesX,
    val name: String,
    val printedTotal: Int,
    val ptcgoCode: String,
    val releaseDate: String,
    val series: String,
    val total: Int,
    val updatedAt: String
)