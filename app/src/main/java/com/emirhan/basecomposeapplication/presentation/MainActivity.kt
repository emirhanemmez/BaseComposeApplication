package com.emirhan.basecomposeapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emirhan.basecomposeapplication.common.BaseMVVMScreen
import com.emirhan.basecomposeapplication.domain.model.Pokemon
import com.emirhan.basecomposeapplication.presentation.detail.PokemonDetailScreen
import com.emirhan.basecomposeapplication.presentation.list.PokemonListScreen
import com.emirhan.basecomposeapplication.presentation.list.PokemonListViewModel
import com.emirhan.basecomposeapplication.ui.theme.BaseComposeApplicationTheme
import com.emirhan.basecomposeapplication.util.NavArgumentType
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseComposeApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonListScreen.route
                    ) {
                        composable(
                            route = Screen.PokemonListScreen.route
                        ) {
                            BaseMVVMScreen<PokemonListViewModel> {
                                PokemonListScreen(gson, navController = navController, it)
                            }
                        }
                        composable(
                            route = Screen.PokemonDetailScreen.route + "/{pokemon}",
                            arguments = listOf(navArgument("pokemon") {
                                type = NavArgumentType(gson, Pokemon::class.java)
                            })
                        ) {
                            it.arguments?.getParcelable<Pokemon>("pokemon")?.let { pokemon ->
                                PokemonDetailScreen(pokemon)
                            }
                        }
                    }
                }
            }
        }
    }
}
