package com.emirhan.basecomposeapplication.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emirhan.basecomposeapplication.data.local.PokemonDatabase
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.data.remote.PokemonApi
import com.emirhan.basecomposeapplication.data.remote.paging_source.PokemonPagingSource
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class PokemonRepositoryImpl(
    private val api: PokemonApi,
    private val db: PokemonDatabase
) : PokemonRepository {

    override suspend fun getPokemons(hp: String?): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                PokemonPagingSource(hp, api)
            }
        ).flow
    }

    override suspend fun getFavourites(): List<PokemonEntity> =
        db.pokemonDao.getFavourites()


    override suspend fun addFavourite(pokemon: PokemonEntity) =
        db.pokemonDao.addFavourite(pokemon)


    override suspend fun removeFromFavourites(pokemon: PokemonEntity) =
        db.pokemonDao.deleteFromFavourites(pokemon)

}
