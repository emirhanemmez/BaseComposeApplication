package com.emirhan.basecomposeapplication.presentation.detail

import androidx.compose.runtime.Composable
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.presentation.detail.components.PokemonItem

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon
) {
    PokemonItem(pokemon = pokemon)
}