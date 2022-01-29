package com.emirhan.basecomposeapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao

    companion object {
        const val DATABASE_NAME = "pokemon_db"
    }
}