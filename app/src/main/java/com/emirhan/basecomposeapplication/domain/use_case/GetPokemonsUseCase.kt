package com.emirhan.basecomposeapplication.domain.use_case

import androidx.paging.PagingData
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(hp: String?): Flow<PagingData<Pokemon>> = repository.getPokemons(hp)
}