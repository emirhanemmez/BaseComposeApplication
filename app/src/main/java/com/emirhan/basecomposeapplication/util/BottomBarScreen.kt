package com.emirhan.basecomposeapplication.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Search : BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Favourites : BottomBarScreen(
        route = "favourites",
        title = "Favourites",
        icon = Icons.Default.Favorite
    )
}
