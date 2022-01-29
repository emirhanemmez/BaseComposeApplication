package com.emirhan.basecomposeapplication.data.remote

import com.emirhan.basecomposeapplication.data.remote.dto.GetPokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("cards")
    suspend fun getPokemonsByHp(
        @Query("q", encoded = true) hp: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): GetPokemonsResponse
}