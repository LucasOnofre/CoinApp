package com.onoffrice.data.helper

import com.google.gson.Gson
import com.onoffrice.data.mapper.ApiErrorMapper
import com.onoffrice.data.model.ErrorResponse
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.utils.ResultWrapper
import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

fun <T> safeApiCall(
    apiCall: suspend () -> T
): Flow<ResultWrapper<T>> {
    return flow {
        emit(
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is UnknownHostException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)

                        if (code == 500 || code == 503) {
                            ResultWrapper.GenericError(code, errorResponse)
                        } else {
                            ResultWrapper.Error(code, errorResponse)
                        }
                    }

                    else -> ResultWrapper.UndefinedError(throwable)
                }
            }
        )
    }
}

private fun convertErrorBody(throwable: HttpException): ApiError? {
    return try {
        throwable.response()?.errorBody()?.let {
            ApiErrorMapper.mapToDomain(Gson().fromJson(it.string(), ErrorResponse::class.java))
        }
    } catch (exception: Exception) {
        null
    }
}
