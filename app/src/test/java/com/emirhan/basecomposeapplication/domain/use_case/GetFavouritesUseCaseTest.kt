package com.emirhan.basecomposeapplication.domain.use_case

import com.emirhan.basecomposeapplication.data.local.entity.PokemonEntity
import com.emirhan.basecomposeapplication.domain.data.repository.FakePokemonRepository
import com.emirhan.basecomposeapplication.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetFavouritesUseCaseTest {

    private lateinit var getFavouriteUseCase: GetFavouritesUseCase
    private lateinit var fakePokemonRepository: FakePokemonRepository

    @Before
    fun setUp() {
        fakePokemonRepository = FakePokemonRepository()
        getFavouriteUseCase = GetFavouritesUseCase(fakePokemonRepository)

        val pokemonsToInsert = mutableListOf<PokemonEntity>()

        for (i in 0 until 20) {
            PokemonEntity(
                id = i.toString(),
                name = "${i}. pokemon",
                artistName = "${i}. pokemon's artist name",
                hp = "${i}. pokemon's hp",
                smallImage = "${i}. pokemon's small image",
                largeImage = "${i}. pokemon's large image"
            )
        }

        runBlocking {
            pokemonsToInsert.forEach {
                fakePokemonRepository.addFavourite(it)
            }
        }
    }

    @Test
    fun `get favourite pokemons`() = runBlocking {
        getFavouriteUseCase.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    assertNotNull(it.data)
                }
                else -> {

                }
            }
        }
    }

}