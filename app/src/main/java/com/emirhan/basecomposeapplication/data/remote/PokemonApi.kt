package com.emirhan.basecomposeapplication.data.remote

import com.emirhan.basecomposeapplication.data.remote.dto.GetPokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("cards")
    suspend fun getPokemons(
        @Query("hp") hp: String
    ): GetPokemonsResponse
}