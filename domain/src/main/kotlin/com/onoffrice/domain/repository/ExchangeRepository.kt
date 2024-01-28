package com.onoffrice.domain.repository

import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun getExchangeList(): Flow<ResultWrapper<List<Exchange>>>
    suspend fun getExchangeDetail(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>>
}
