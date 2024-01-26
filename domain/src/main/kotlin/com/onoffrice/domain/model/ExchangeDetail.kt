package com.onoffrice.domain.model

data class ExchangeDetail(
    val dataEnd: String = "",
    val dataOrderBookEnd: String = "",
    val dataOrderBookStart: String = "",
    val dataStart: String = "",
    val dataTradeCount: Int = 0,
    val dataTradeEnd: String = "",
    val dataTradeStart: String = "",
    val exchangeId: String = "",
    val icons: List<Icon> = listOf(),
    val metricId: List<String> = listOf(),
    val name: String = "",
    val volume1dayUsd: Double = 0.0,
    val volume1hrsUsd: Double = 0.0,
    val volume1mthUsd: Double = 0.0,
    val website: String = ""
)

data class Icon(
    val assetId: String = "",
    val exchangeId: String = "",
    val url: String = ""
)