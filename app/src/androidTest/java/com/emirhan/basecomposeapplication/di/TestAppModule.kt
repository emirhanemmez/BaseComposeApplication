package com.emirhan.basecomposeapplication.di


import android.app.Application
import androidx.room.Room
import com.emirhan.basecomposeapplication.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            PokemonDatabase::class.java
        ).build()
    }
}