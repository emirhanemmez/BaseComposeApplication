package com.emirhan.basecomposeapplication.domain.use_case

import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import com.emirhan.basecomposeapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(pokemon: Pokemon): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            repository.addFavourite(pokemon.toPokemonEntity())
            emit(Resource.Success(Unit))
        } catch (e: IOException) {
            emit(Resource.Error("An error occurred!"))
        } catch (e: Exception) {
            Timber.tag("Error").e(e.localizedMessage)
        }
    }
}