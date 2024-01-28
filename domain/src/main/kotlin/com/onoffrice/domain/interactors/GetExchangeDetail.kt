package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

class GetExchangeDetailImpl(private val repository: ExchangeRepository) : GetExchangeDetail {
    override suspend fun execute(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>> {
        return repository.getExchangeDetail(exchangeId)
    }
}

interface GetExchangeDetail {
    suspend fun execute(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>>
}