package com.emirhan.basecomposeapplication.presentation.favourites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.emirhan.basecomposeapplication.common.BaseResponseState
import com.emirhan.basecomposeapplication.common.BaseViewModel
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.use_case.GetFavouritesUseCase
import com.emirhan.basecomposeapplication.domain.use_case.RemoveFromFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase
) : BaseViewModel() {

    private val _removeState = mutableStateOf<String>("")
    val removeState: State<String> = _removeState


    private val _favouriteListState: MutableState<BaseResponseState<List<Pokemon>>> =
        mutableStateOf(BaseResponseState())
    val favouriteListState: State<BaseResponseState<List<Pokemon>>> = _favouriteListState

    init {
        getFavouritePokemons()
    }

    fun getFavouritePokemons() {
        handleRequest(getFavouritesUseCase(), {
            _favouriteListState.value = BaseResponseState(it.toMutableList())
        })
    }

    fun removeFromFavourites(pokemon: Pokemon) {
        handleRequest(removeFromFavouritesUseCase(pokemon), {
            _favouriteListState.value =
                BaseResponseState(_favouriteListState.value.response?.toMutableList()?.apply {
                    remove(pokemon)
                })

            _removeState.value = pokemon.name
        })
    }
}