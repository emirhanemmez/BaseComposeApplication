package com.emirhan.basecomposeapplication.util

sealed class Screen(val route: String) {
    object PokemonDetailScreen : Screen("pokemon_detail")
}
