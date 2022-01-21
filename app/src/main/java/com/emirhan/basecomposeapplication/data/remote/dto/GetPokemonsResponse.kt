package com.emirhan.basecomposeapplication.data.remote.dto

data class GetPokemonsResponse(
    val count: Int,
    val data: List<Data>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)