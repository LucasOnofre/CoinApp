package com.onoffrice.data.mapper

import com.onoffrice.data.model.ExchangeResponse
import com.onoffrice.domain.model.Exchange

object ExchangeMapper : BaseMapper<ExchangeResponse, Exchange> {
    override fun mapFromDomain(domain: Exchange): ExchangeResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ExchangeResponse): Exchange {
        return Exchange(
            name = response.name ?: "",
            exchangeId = response.exchangeId ?: "",
            volume1dayUsd = response.volume1dayUsd ?: 0.0
        )
    }
}
