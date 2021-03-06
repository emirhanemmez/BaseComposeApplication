package com.emirhan.basecomposeapplication.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.emirhan.basecomposeapplication.data.remote.dto.Images
import com.emirhan.basecomposeapplication.domain.model.Pokemon

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onItemClick: (Pokemon) -> Unit,
    onLongPress: (Pokemon) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        backgroundColor = Color.LightGray,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { },
                        onTap = { onItemClick(pokemon) },
                        onLongPress = { onLongPress(pokemon) }
                    )
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .requiredHeight(200.dp),
                contentScale = ContentScale.FillBounds,
                painter = rememberImagePainter(pokemon.images.large),
                contentDescription = "Image of the pokemon"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                text = pokemon.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun PokemonListItemPreview() = PokemonListItem(
    pokemon = Pokemon("1", "Pikachu", "Ash", "99", Images("", "")),
    onItemClick = {},
    onLongPress = {}
)