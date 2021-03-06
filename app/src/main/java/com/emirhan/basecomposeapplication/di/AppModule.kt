package com.emirhan.basecomposeapplication.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.emirhan.basecomposeapplication.data.local.PokemonDatabase
import com.emirhan.basecomposeapplication.data.remote.PokemonApi
import com.emirhan.basecomposeapplication.data.repository.PokemonRepositoryImpl
import com.emirhan.basecomposeapplication.domain.repository.PokemonRepository
import com.emirhan.basecomposeapplication.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            PokemonDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideApi(): PokemonApi {
        val loggingInterceptor = HttpLoggingInterceptor {
            Timber.tag("HttpClient").i(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PokemonApi::class.java)

    }

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi, db: PokemonDatabase): PokemonRepository {
        return PokemonRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()
}