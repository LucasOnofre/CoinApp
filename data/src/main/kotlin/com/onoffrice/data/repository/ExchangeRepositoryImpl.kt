package com.onoffrice.data.repository

import com.onoffrice.data.api.ExchangeApi
import com.onoffrice.data.mapper.ExchangeDetailMapper
import com.onoffrice.data.mapper.ExchangeMapper
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExchangeRepositoryImpl(val api: ExchangeApi) : ExchangeRepository {
    override suspend fun getExchangeList(): Flow<ResultWrapper<List<Exchange>>> {
        return flow {
            try {
                emit(
                    ResultWrapper.Success(
                        api.getExchangeList().map { exchange ->
                            ExchangeMapper.mapToDomain(exchange)
                        }
                    )
                )
            } catch (ex: Exception) {
                emit(ResultWrapper.Error())
            }
        }
    }

    override suspend fun getExchangeDetail(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>> {
        return flow {
            try {
                emit(
                    ResultWrapper.Success(
                        api.getExchangeDetail(exchangeId).map { exchange ->
                            ExchangeDetailMapper.mapToDomain(exchange)
                        }
                    )
                )
            } catch (ex: Exception) {
                emit(ResultWrapper.Error())
            }
        }
    }
}
