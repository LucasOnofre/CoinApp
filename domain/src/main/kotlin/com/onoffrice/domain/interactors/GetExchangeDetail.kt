package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.utils.ResultWrapper
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.WrapperUseCase
import kotlinx.coroutines.flow.Flow

class GetExchangeDetail(private val repository: ExchangeRepository) :
    WrapperUseCase<String, List<ExchangeDetail>>() {
    override fun executeUseCase(requestValues: String): Flow<ResultWrapper<List<ExchangeDetail>>> {
        return repository.getExchangeDetail(requestValues)
    }
}
