package com.emirhan.basecomposeapplication.domain.repository

import androidx.paging.PagingData
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(hp: String?): Flow<PagingData<Pokemon>>

    suspend fun getFavourites(): List<PokemonEntity>

    suspend fun addFavourite(pokemon: PokemonEntity)

    suspend fun removeFromFavourites(pokemon: PokemonEntity)
}