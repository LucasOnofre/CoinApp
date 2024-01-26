package com.onoffrice.data.model

import com.google.gson.annotations.SerializedName

class ExchangeDetailResponse(
    @SerializedName("exchange_id") val exchangeId: String? = null,
    @SerializedName("website") val website: String? = null,
    @SerializedName("dataEnd") val dataEnd: String? = null,
    @SerializedName("data_orderbook_end") val dataOrderBookEnd: String? = null,
    @SerializedName("data_orderbook_start") val dataOrderBookStart: String? = null,
    @SerializedName("data_start") val dataStart: String? = null,
    @SerializedName("data_trade_count") val dataTradeCount: Int? = null,
    @SerializedName("data_trade_end") val dataTradeEnd: String? = null,
    @SerializedName("data_trade_start") val dataTradeStart: String? = null,
    @SerializedName("icons") val icons: List<IconResponse>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double? = null,
    @SerializedName("volume_1hrs_usd") val volume1hrsUsd: Double? = null,
    @SerializedName("volume_1mth_usd") val volume1mthUsd: Double? = null
)

data class IconResponse(
    @SerializedName("assetId") val assetId: String? = null,
    @SerializedName("exchangeId") val exchangeId: String? = null,
    @SerializedName("url") val url: String? = null
)