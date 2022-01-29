package com.emirhan.basecomposeapplication.data.remote.dto

import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.google.gson.annotations.SerializedName

data class Data(
    val abilities: List<Ability>,
    val artist: String,
    val attacks: List<Attack>,
    @SerializedName("cardmarket") val cardMarket: CardMarket,
    val convertedRetreatCost: Int,
    val evolvesFrom: String,
    val evolvesTo: List<String>,
    val flavorText: String,
    val hp: String,
    val id: String,
    val images: Images,
    val legalities: Legalities,
    val level: String,
    val name: String,
    val nationalPokedexNumbers: List<Int>,
    val number: String,
    val rarity: String,
    val regulationMark: String,
    val resistances: List<Resistance>,
    val retreatCost: List<String>,
    val rules: List<String>,
    val set: Set,
    val subtypes: List<String>,
    val supertype: String,
    @SerializedName("tcgplayer") val tcgPlayer: Tcgplayer,
    val types: List<String>,
    val weaknesses: List<Weakness>
) {
    fun toPokemon() = Pokemon(id = id, name = name, artistName = artist, hp = hp, images = images)

    fun toPokemonEntity() =
        PokemonEntity(
            id = id,
            name = name,
            artistName = artist,
            hp = hp,
            smallImage = images.small,
            largeImage = images.large
        )
}

