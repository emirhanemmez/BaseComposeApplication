package com.emirhan.basecomposeapplication.presentation.list

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.emirhan.basecomposeapplication.presentation.list.components.PokemonList
import com.emirhan.basecomposeapplication.presentation.list.components.SearchView
import com.emirhan.basecomposeapplication.util.Screen
import com.google.gson.Gson
import com.talhafaki.composablesweettoast.util.SweetToastUtil

@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun PokemonListScreen(
    gson: Gson,
    navController: NavController,
    viewModel: PokemonListViewModel
) {
    val pokemonListItems = viewModel.searchedPokemons.collectAsLazyPagingItems()
    val addedPokemonName by viewModel.addFavouriteLiveData.observeAsState(null)

    val state = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        SearchView(
            hint = "Search pokemons by hp",
            onSearch = {
                viewModel.getPokemons(it)
            }
        )

        PokemonList(
            pokemons = pokemonListItems,
            onItemClick = { pokemon ->
                val json = Uri.encode(gson.toJson(pokemon))
                navController.navigate(Screen.PokemonDetailScreen.route + "/$json") {
                    restoreState = true
                }
            },
            onLongItemClick = { pokemon ->
                viewModel.addFavourite(pokemon)
            }
        )
    }

    if (!addedPokemonName.isNullOrEmpty()) {
        SweetToastUtil.SweetSuccess(
            message = "$addedPokemonName is added to favourites!"
        )
        viewModel.restoreFavouriteState()
    }
}
