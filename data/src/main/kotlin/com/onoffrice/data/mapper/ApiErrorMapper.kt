package com.onoffrice.data.mapper

import com.onoffrice.data.model.ErrorResponse
import com.onoffrice.domain.model.ApiError

object ApiErrorMapper : BaseMapper<ErrorResponse, ApiError> {

    override fun mapFromDomain(domain: ApiError): ErrorResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ErrorResponse): ApiError {
        return ApiError(
            error = response.error ?: ""
        )
    }
}
