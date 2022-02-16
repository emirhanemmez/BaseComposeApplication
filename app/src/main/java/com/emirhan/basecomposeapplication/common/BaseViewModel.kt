package com.emirhan.basecomposeapplication.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.emirhan.basecomposeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    private val mutableBaseState = mutableStateOf(BaseState())
    val baseState: State<BaseState> = mutableBaseState

    private val _mutablePaginatedState = MutableStateFlow<PagingData<Any>>(PagingData.empty())
    val mutablePaginatedState: StateFlow<PagingData<Any>> = _mutablePaginatedState

    fun <T> handleRequest(
        flow: Flow<Resource<T>>,
        onSuccess: (T) -> Unit,
        onError: ((String) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        flow.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { response ->
                        withContext(Dispatchers.Main) {
                            onSuccess.invoke(response)
                            mutableBaseState.value = BaseState()
                        }
                    }
                }
                is Resource.Error -> {
                    withContext(Dispatchers.Main) {
                        if (onError != null)
                            onError.invoke(result.message ?: "Unexpected error occured!")
                        else {
                            mutableBaseState.value =
                                BaseState(error = result.message ?: "Unexpected error occured!")
                        }
                    }
                }
                is Resource.Loading -> {
                    withContext(Dispatchers.Main) {
                        onLoading?.invoke()
                        if (onLoading != null)
                            onLoading.invoke()
                        else
                            mutableBaseState.value = BaseState(true)
                    }
                }
            }
            onComplete?.invoke()
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun handlePaginatedRequest(

    ) {

    }
}