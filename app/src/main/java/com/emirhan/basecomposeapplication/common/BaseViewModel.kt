package com.emirhan.basecomposeapplication.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class BaseViewModel : ViewModel() {
    private val mutableBaseState = mutableStateOf(BaseState())
    val baseState: State<BaseState> = mutableBaseState

    fun <T> handleRequest(
        flow: Flow<Resource<T>>,
        onSuccess: ((T) -> Unit),
        onError: ((String) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        flow.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { response ->
                        onSuccess.invoke(response)
                        mutableBaseState.value = BaseState()
                    }
                }
                is Resource.Error -> {
                    if (onError != null)
                        onError.invoke(result.message ?: "Unexpected error occured!")
                    else {
                        mutableBaseState.value =
                            BaseState(error = result.message ?: "Unexpected error occured!")
                    }
                }
                is Resource.Loading -> {
                    onLoading?.invoke()
                    if (onLoading != null)
                        onLoading.invoke()
                    else
                        mutableBaseState.value = BaseState(true)
                }
            }
            onComplete?.invoke()
        }.launchIn(viewModelScope)
    }
}