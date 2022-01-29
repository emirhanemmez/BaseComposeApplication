package com.emirhan.basecomposeapplication.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.emirhan.basecomposeapplication.domain.model.Pokemon

@Composable
fun PokemonItem(pokemon: Pokemon) {
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
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
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