package com.onoffrice.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class WrapperUseCase<in REQUEST, RESPONSE> {

    operator fun invoke(requestValues: REQUEST): Flow<ResultWrapper<RESPONSE>> {
        return executeUseCase(requestValues)
    }

    abstract fun executeUseCase(requestValues: REQUEST): Flow<ResultWrapper<RESPONSE>>
}
