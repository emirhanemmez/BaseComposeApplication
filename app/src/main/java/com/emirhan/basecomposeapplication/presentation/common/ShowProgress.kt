package com.emirhan.basecomposeapplication.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowProgress() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 56.dp)
        )
    }
}