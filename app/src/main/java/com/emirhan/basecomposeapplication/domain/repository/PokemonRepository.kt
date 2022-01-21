package com.emirhan.basecomposeapplication.domain.repository

import com.emirhan.basecomposeapplication.data.remote.dto.GetPokemonsResponse

interface PokemonRepository {
    suspend fun getPokemons(hp: String): GetPokemonsResponse
}