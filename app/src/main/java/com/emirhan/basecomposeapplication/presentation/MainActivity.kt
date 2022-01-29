package com.emirhan.basecomposeapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.paging.ExperimentalPagingApi
import com.emirhan.basecomposeapplication.presentation.main.MainScreen
import com.emirhan.basecomposeapplication.ui.theme.BaseComposeApplicationTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPagingApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseComposeApplicationTheme {
                MainScreen(gson = gson)
            }
        }
    }
}
