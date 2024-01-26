package com.onoffrice.data.repository

import com.onoffrice.data.api.ExchangeApi
import com.onoffrice.data.helper.safeApiCall
import com.onoffrice.data.mapper.ExchangeDetailMapper
import com.onoffrice.data.mapper.ExchangeMapper
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.utils.ResultWrapper
import com.onoffrice.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class ExchangeRepositoryImpl(val api: ExchangeApi) : ExchangeRepository {
    override fun getExchangeList(): Flow<ResultWrapper<List<Exchange>>> {
        return safeApiCall {
            api.getExchangeList().map { exchange ->
                ExchangeMapper.mapToDomain(exchange)
            }
        }
    }

    override fun getExchangeDetail(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>> {
        return safeApiCall {
            api.getExchangeDetail(exchangeId).map { exchange ->
                ExchangeDetailMapper.mapToDomain(exchange)
            }
        }
    }
}
