package com.onoffrice.core.extensions

import android.os.Handler
import android.os.Looper
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<UIState<T>>.wrapResponse(
    resultWrapper: ResultWrapper<T>,
    forNavigation: Boolean = true
) {
    when (resultWrapper) {
        is ResultWrapper.Success -> {
            handleState(UIState.Success(resultWrapper.value), forNavigation)
        }

        is ResultWrapper.Error -> {
            val error = resultWrapper.error
            handleState(UIState.Error(error), forNavigation)
        }

        is ResultWrapper.GenericError -> {
            val error = resultWrapper.error
            handleState(UIState.GenericError(error), forNavigation)
        }

        is ResultWrapper.NetworkError -> {
            handleState(UIState.NetworkError, forNavigation)
        }

        is ResultWrapper.UndefinedError -> {
            val throwable = resultWrapper.exception
            handleState(UIState.UndefinedError(throwable), forNavigation)
        }
    }
}

fun <T> MutableStateFlow<UIState<T>>.handleState(
    state: UIState<T>,
    forNavigation: Boolean
) {
    if (forNavigation) updateState(state, UIState.Idle)
    else value = state
}

fun <T> MutableStateFlow<T>.updateState(newValue: T, initialValue: T, delay: Long = 100) {
    value = newValue

    Handler(Looper.getMainLooper()).postDelayed({
        value = initialValue
    }, delay)
}