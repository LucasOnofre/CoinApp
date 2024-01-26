package com.onoffrice.data.api

import com.onoffrice.data.BuildConfig
import com.onoffrice.data.model.ExchangeDetailResponse
import com.onoffrice.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApi {
    @GET("/v1/exchanges")
    @BaseUrl(BuildConfig.BASE_URL)
    suspend fun getExchangeList(): List<ExchangeResponse>

    @GET("/v1/exchanges/{exchange_id}")
    @BaseUrl(BuildConfig.BASE_URL)
    suspend fun getExchangeDetail(
        @Path("exchange_id") exchangeId: String,
    ): List<ExchangeDetailResponse>
}