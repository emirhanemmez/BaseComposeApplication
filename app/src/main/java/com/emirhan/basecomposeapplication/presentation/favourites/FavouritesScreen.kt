package com.emirhan.basecomposeapplication.presentation.favourites

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.emirhan.basecomposeapplication.presentation.common.EmptyListScreen
import com.emirhan.basecomposeapplication.presentation.list.components.PokemonListItem
import com.emirhan.basecomposeapplication.util.Screen
import com.google.gson.Gson
import com.talhafaki.composablesweettoast.util.SweetToastUtil

@ExperimentalFoundationApi
@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    navController: NavController,
    gson: Gson
) {
    val favouriteListState = viewModel.favouriteListState.value
    val removedPokemon by viewModel.removeState.observeAsState()

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.testTag("Favourite Screen")
    ) {
        favouriteListState.response?.let { list ->
            itemsIndexed(
                items = list,
                itemContent = { _, pokemon ->
                    PokemonListItem(
                        pokemon = pokemon,
                        onItemClick = {
                            val json = Uri.encode(gson.toJson(pokemon))
                            navController.navigate(Screen.PokemonDetailScreen.route + "/$json") {
                                restoreState = true
                            }
                        },
                        onLongPress = {
                            viewModel.removeFromFavourites(pokemon)
                        }
                    )
                }
            )
        }
    }

    if (favouriteListState.response.isNullOrEmpty()) {
        EmptyListScreen(text = "No Result") {
            viewModel.getFavouritePokemons()
        }
    }

    if (!removedPokemon.isNullOrEmpty()) {
        SweetToastUtil.SweetSuccess(
            message = "$removedPokemon is removed from favourites"
        )
        viewModel.restoreRemoveState()
    }
}