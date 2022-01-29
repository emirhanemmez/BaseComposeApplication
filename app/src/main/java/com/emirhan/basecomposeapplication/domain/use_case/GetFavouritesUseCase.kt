package com.emirhan.basecomposeapplication.domain.use_case

import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import com.emirhan.basecomposeapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<Resource<List<Pokemon>>> = flow {
        try {
            emit(Resource.Loading())
            val pokemons = repository.getFavourites()
                .map {
                    it.toPokemon()
                }
            emit(Resource.Success(pokemons))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        } catch (e: Exception) {
            Timber.tag("Error").e(e.localizedMessage)
        }
    }
}