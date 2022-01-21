package com.emirhan.basecomposeapplication.presentation.list

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.emirhan.basecomposeapplication.presentation.Screen
import com.emirhan.basecomposeapplication.presentation.list.components.PokemonListItem
import com.emirhan.basecomposeapplication.presentation.list.components.SearchBar
import com.google.gson.Gson

@ExperimentalFoundationApi
@Composable
fun PokemonListScreen(
    gson: Gson,
    navController: NavController,
    viewModel: PokemonListViewModel
) {
    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            hint = "Search pokemon",
            onSearch = {
                viewModel.getPokemons(it)
            }
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            state.response?.let {
                items(it) { pokemon ->
                    PokemonListItem(
                        pokemon = pokemon,
                        onItemClick = {
                            val json = Uri.encode(gson.toJson(pokemon))
                            navController.navigate(Screen.PokemonDetailScreen.route + "/$json")
                        }
                    )
                }
            }
        }
    }
}
