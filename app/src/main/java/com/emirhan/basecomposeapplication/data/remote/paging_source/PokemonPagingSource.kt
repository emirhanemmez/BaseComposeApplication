package com.emirhan.basecomposeapplication.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emirhan.basecomposeapplication.data.remote.PokemonApi
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val hp: String?,
    private val api: PokemonApi
) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.getPokemonsByHp(
                if (hp.isNullOrEmpty()) null else "hp:$hp",
                nextPage,
                params.loadSize
            )
            val pokemonList = response.data.map { it.toPokemon() }
            LoadResult.Page(
                data = pokemonList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (pokemonList.isEmpty()) null else nextPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}