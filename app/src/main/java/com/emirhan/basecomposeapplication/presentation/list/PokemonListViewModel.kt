package com.emirhan.basecomposeapplication.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirhan.basecomposeapplication.common.BaseViewModel
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.use_case.AddFavouriteUseCase
import com.emirhan.basecomposeapplication.domain.use_case.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase
) : BaseViewModel() {

    private val _addFavouriteLiveData = MutableLiveData<String>("")
    val addFavouriteLiveData: LiveData<String> = _addFavouriteLiveData

    private val _searchedPokemons = MutableStateFlow<PagingData<Pokemon>>(PagingData.empty())
    val searchedPokemons: StateFlow<PagingData<Pokemon>> = _searchedPokemons

    fun getPokemons(hp: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        getPokemonsUseCase(hp).cachedIn(viewModelScope).collect {
            _searchedPokemons.value = it
        }
    }

    fun addFavourite(pokemon: Pokemon) =
        handleRequest(addFavouriteUseCase(pokemon), {
            _addFavouriteLiveData.value = pokemon.name
        }, onComplete = {
            _addFavouriteLiveData.postValue(null)
        })
}
