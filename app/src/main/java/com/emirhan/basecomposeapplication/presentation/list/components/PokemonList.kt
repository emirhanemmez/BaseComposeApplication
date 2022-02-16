package com.emirhan.basecomposeapplication.presentation.list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.presentation.common.handlePagingResult
import com.emirhan.basecomposeapplication.util.items

@ExperimentalFoundationApi
@Composable
fun PokemonList(
    pokemons: LazyPagingItems<Pokemon>,
    onLongItemClick: (Pokemon) -> Unit,
    onItemClick: (Pokemon) -> Unit
) {
    val result = handlePagingResult(pagingItems = pokemons)

    if (result) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(pokemons) {
                it?.let { pokemon ->
                    PokemonListItem(
                        pokemon = pokemon,
                        onItemClick = { onItemClick.invoke(it) },
                        onLongPress = { onLongItemClick.invoke(it) }
                    )
                }
            }
        }
    }
}
