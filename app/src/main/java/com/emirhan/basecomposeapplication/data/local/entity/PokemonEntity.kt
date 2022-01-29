package com.emirhan.basecomposeapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emirhan.basecomposeapplication.data.remote.dto.Images
import com.emirhan.basecomposeapplication.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val artistName: String,
    val hp: String,
    val smallImage: String,
    val largeImage: String
) {
    fun toPokemon(): Pokemon =
        Pokemon(id, name, artistName, hp, Images(smallImage, largeImage))
}
