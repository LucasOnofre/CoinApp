package com.onoffrice.data.mapper

import com.onoffrice.data.model.ExchangeDetailResponse
import com.onoffrice.data.model.ExchangeResponse
import com.onoffrice.data.model.IconResponse
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.model.Icon

object ExchangeDetailMapper : BaseMapper<ExchangeDetailResponse, ExchangeDetail> {
    override fun mapFromDomain(domain: ExchangeDetail): ExchangeDetailResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ExchangeDetailResponse): ExchangeDetail {
        return ExchangeDetail(
            name = response.name ?: "",
            exchangeId = response.exchangeId ?: "",
            dataEnd = response.dataEnd ?: "",
            dataOrderBookEnd = response.dataOrderBookEnd ?: "",
            dataOrderBookStart = response.dataOrderBookStart ?: "",
            dataStart = response.dataStart ?: "",
            dataTradeCount = response.dataTradeCount ?: 0,
            dataTradeEnd = response.dataTradeEnd ?: "",
            dataTradeStart = response.dataTradeStart ?: "",
            volume1dayUsd = response.volume1dayUsd ?: 0.0,
            volume1hrsUsd = response.volume1hrsUsd ?: 0.0,
            volume1mthUsd = response.volume1mthUsd ?: 0.0,
            website = response.website ?: "",
            icons = mapIconsList(response.icons) ?: listOf()
        )
    }

    private fun mapIconsList(icons: List<IconResponse>?): List<Icon>? {
        return icons?.map {
            Icon(
                assetId = it.assetId ?: "",
                exchangeId = it.exchangeId ?: "",
                url = it.url ?: ""
            )
        }
    }
}
