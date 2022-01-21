package com.emirhan.basecomposeapplication.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.emirhan.basecomposeapplication.common.BaseResponseState
import com.emirhan.basecomposeapplication.common.BaseViewModel
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.domain.use_case.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BaseViewModel() {

    private val mutableState: MutableState<BaseResponseState<List<Pokemon>>> =
        mutableStateOf(BaseResponseState())
    val state: MutableState<BaseResponseState<List<Pokemon>>> = mutableState

    init {
        getPokemons()
    }

    fun getPokemons(hp: String = "99") {
        handleRequest(getPokemonsUseCase(hp), {
            mutableState.value = BaseResponseState(it)
        })
    }
}
