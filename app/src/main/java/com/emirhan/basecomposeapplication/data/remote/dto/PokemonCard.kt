package com.emirhan.basecomposeapplication.data.remote.dto

import android.os.Parcelable
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonCard(
    val artist: String,
    val attacks: List<Attack>,
    val convertedRetreatCost: Int,
    val hp: String,
    val id: String,
    val imageUrl: String,
    val imageUrlHiRes: String,
    val name: String,
    val nationalPokedexNumber: Int,
    val number: String,
    val rarity: String,
    val retreatCost: List<String>,
    val series: String,
    val set: String,
    val setCode: String,
    val subtype: String,
    val supertype: String,
    val types: List<String>,
    val weaknesses: List<Weakness>
) : Parcelable {
    fun toPokemon(): Pokemon =
        Pokemon(
            id = id,
            name = name,
            artistName = artist,
            image = imageUrl
        )
}