package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.utils.ResultWrapper
import com.onoffrice.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class GetExchangeList(private val repository: ExchangeRepository) {
    fun executeUseCase(): Flow<ResultWrapper<List<Exchange>>> {
        return repository.getExchangeList()
    }
}
