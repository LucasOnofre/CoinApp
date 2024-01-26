package com.onoffrice.data.model

import com.google.gson.annotations.SerializedName

class ExchangeResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("exchange_id") val exchangeId: String? = null,
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double? = null
)