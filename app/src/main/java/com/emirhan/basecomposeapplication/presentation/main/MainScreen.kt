package com.emirhan.basecomposeapplication.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.emirhan.basecomposeapplication.presentation.main.components.BottomBar
import com.emirhan.basecomposeapplication.presentation.main.components.BottomNavGraph
import com.google.gson.Gson
import kotlin.math.roundToInt

@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(gson: Gson) {
    val bottomBarHeight = 56.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .height(bottomBarHeight)
                    .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) },
                navController = navController
            )
        }
    ) {
        BottomNavGraph(gson = gson, navController = navController)
    }
}