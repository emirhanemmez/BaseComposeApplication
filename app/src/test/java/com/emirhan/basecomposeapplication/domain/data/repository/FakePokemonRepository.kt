package com.emirhan.basecomposeapplication.domain.data.repository

import androidx.paging.PagingData
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository : PokemonRepository {

    private val pokemons = mutableListOf<PokemonEntity>()

    override suspend fun getPokemons(hp: String?): Flow<PagingData<Pokemon>> {
        return flow { }
    }

    override suspend fun getFavourites(): List<PokemonEntity> {
        return pokemons
    }

    override suspend fun addFavourite(pokemon: PokemonEntity) {
        pokemons.add(pokemon)
    }

    override suspend fun removeFromFavourites(pokemon: PokemonEntity) {
        pokemons.remove(pokemon)
    }
}