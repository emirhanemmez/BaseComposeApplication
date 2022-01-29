package com.emirhan.basecomposeapplication.domain.model

import android.os.Parcelable
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.data.remote.dto.Images
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val artistName: String,
    val hp: String,
    val images: Images
) : Parcelable {
    fun toPokemonEntity(): PokemonEntity =
        PokemonEntity(
            id = id,
            name = name,
            artistName = artistName,
            hp = hp,
            smallImage = images.small,
            largeImage = images.large
        )
}
