package com.emirhan.basecomposeapplication.presentation.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.emirhan.basecomposeapplication.common.BaseMVVMScreen
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.presentation.detail.PokemonDetailScreen
import com.emirhan.basecomposeapplication.presentation.favourites.FavouritesScreen
import com.emirhan.basecomposeapplication.presentation.favourites.FavouritesViewModel
import com.emirhan.basecomposeapplication.presentation.list.PokemonListScreen
import com.emirhan.basecomposeapplication.presentation.list.PokemonListViewModel
import com.emirhan.basecomposeapplication.util.BottomBarScreen
import com.emirhan.basecomposeapplication.util.NavArgumentType
import com.emirhan.basecomposeapplication.util.Screen
import com.google.gson.Gson

@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun BottomNavGraph(
    gson: Gson,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Search.route) {
        composable(route = BottomBarScreen.Search.route) {
            BaseMVVMScreen<PokemonListViewModel> {
                PokemonListScreen(gson, navController = navController, it)
            }
        }
        composable(
            route = Screen.PokemonDetailScreen.route + "/{pokemon}",
            arguments = listOf(navArgument("pokemon") {
                type = NavArgumentType(gson, Pokemon::class.java)
            })
        ) {
            it.arguments?.getParcelable<Pokemon>("pokemon")?.let { pokemon ->
                PokemonDetailScreen(pokemon)
            }
        }
        composable(route = BottomBarScreen.Favourites.route) {
            BaseMVVMScreen<FavouritesViewModel> {
                FavouritesScreen(it, navController, gson)
            }
        }
    }
}