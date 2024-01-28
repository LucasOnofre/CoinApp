package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

class GetExchangeListImpl(
    private val repository: ExchangeRepository) : GetExchangeList {
    override suspend fun execute(): Flow<ResultWrapper<List<Exchange>>> {
        return repository.getExchangeList()
    }
}

interface GetExchangeList {
    suspend fun execute(): Flow<ResultWrapper<List<Exchange>>>
}