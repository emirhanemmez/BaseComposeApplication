package com.emirhan.basecomposeapplication.presentation.common

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.talhafaki.composablesweettoast.util.SweetToastUtil
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun <T : Any> handlePagingResult(
    pagingItems: LazyPagingItems<T>
): Boolean {
    pagingItems.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShowProgress()
                false
            }
            error != null -> {
                SweetToastUtil.SweetError(message = parseErrorMessage(error))
                EmptyPaginatedListScreen(pagingItems = pagingItems)
                false
            }
            pagingItems.itemCount == 0 -> {
                EmptyPaginatedListScreen<T>()
                false
            }
            else -> true
        }
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Internet Unavailable."
        }
        else -> {
            "Unknown Error."
        }
    }
}