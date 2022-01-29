package com.emirhan.basecomposeapplication.presentation.list

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.emirhan.basecomposeapplication.util.Screen
import com.emirhan.basecomposeapplication.presentation.list.components.PokemonList
import com.emirhan.basecomposeapplication.presentation.list.components.SearchBar
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
    val addedPokemonName = viewModel.addFavouriteState.value

    Column(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            hint = "Search pokemon",
            onSearch = {
                viewModel.getPokemons(it)
            }
        )

        PokemonList(
            pokemons = pokemonListItems,
            onItemClick = { pokemon ->
                val json = Uri.encode(gson.toJson(pokemon))
                navController.navigate(Screen.PokemonDetailScreen.route + "/$json")
            },
            onLongItemClick = { pokemon ->
                viewModel.addFavourite(pokemon)
            }
        )
    }

    if (addedPokemonName.isNotEmpty()) {
        SweetToastUtil.SweetSuccess(
            message = "$addedPokemonName added to favourites!"
        )
    }
}
