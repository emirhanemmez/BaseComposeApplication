package com.emirhan.basecomposeapplication.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhan.basecomposeapplication.presentation.common.ShowProgress
import com.talhafaki.composablesweettoast.util.SweetToastUtil

@Composable
inline fun <reified T : BaseViewModel> BaseMVVMScreen(
    baseViewModel: T = hiltViewModel(),
    content: (@Composable (viewModel: T) -> Unit)
) {
    val baseState = baseViewModel.baseState.value

    Box(modifier = Modifier.fillMaxSize()) {
        content.invoke(baseViewModel)

        if (!baseState.error.isNullOrBlank()) {
            SweetToastUtil.SweetError(message = baseState.error!!)
        }

        if (baseState.isLoading) {
            ShowProgress()
        }
    }
}
