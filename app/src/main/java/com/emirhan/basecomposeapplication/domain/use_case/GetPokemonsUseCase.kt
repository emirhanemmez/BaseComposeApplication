package com.emirhan.basecomposeapplication.domain.use_case

import com.emirhan.basecomposeapplication.common.Resource
import com.emirhan.basecomposeapplication.data.remote.dto.toPokemon
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(hp: String): Flow<Resource<List<Pokemon>>> = flow {
        try {
            emit(Resource.Loading())
            val pokemons = repository.getPokemons(hp).data.map { it.toPokemon() }
            emit(Resource.Success(pokemons))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        } catch (e: Exception) {
            Timber.tag("Error").e(e.localizedMessage)
        }
    }
}