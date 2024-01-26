package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.utils.ResultWrapper
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.WrapperUseCase
import kotlinx.coroutines.flow.Flow

class GetExchangeList(private val repository: ExchangeRepository) :
    WrapperUseCase<Unit, List<Exchange>>() {
    override fun executeUseCase(requestValues: Unit): Flow<ResultWrapper<List<Exchange>>> {
        return repository.getExchangeList()
    }
}
