package com.emirhan.basecomposeapplication.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.emirhan.basecomposeapplication.common.BaseMVVMScreen
import com.emirhan.basecomposeapplication.di.AppModule
import com.emirhan.basecomposeapplication.presentation.MainActivity
import com.emirhan.basecomposeapplication.presentation.favourites.FavouritesScreen
import com.emirhan.basecomposeapplication.presentation.favourites.FavouritesViewModel
import com.emirhan.basecomposeapplication.util.BottomBarScreen
import com.emirhan.basecomposeapplication.ui.theme.BaseComposeApplicationTheme
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalPagingApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class FavouritesScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.setContent {
            val navController = rememberNavController()
            BaseComposeApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = BottomBarScreen.Favourites.route
                ) {
                    composable(route = BottomBarScreen.Favourites.route) {
                        BaseMVVMScreen<FavouritesViewModel> {
                            FavouritesScreen(
                                viewModel = it,
                                navController = navController,
                                gson = Gson()
                            )
                        }
                    }
                }
            }
        }
    }

    @Test
    fun clickListItem() {
        //val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag("bottom favourites").performClick()
        composeRule.onNodeWithTag("Favourite Screen").assertIsDisplayed()
    }
}