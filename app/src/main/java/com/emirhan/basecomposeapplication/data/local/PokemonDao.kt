package com.emirhan.basecomposeapplication.data.local

import androidx.room.*
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFromFavourites(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon")
    fun getFavourites(): List<PokemonEntity>
}