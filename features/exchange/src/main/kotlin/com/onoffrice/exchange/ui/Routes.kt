package com.onoffrice.exchange.ui

const val EXCHANGE_DETAIL_ROUTE_ARG = "selected_exchange"

sealed class Routes(val rout: String) {
    object ExchangeList : Routes("exchange_list")
    object ExchangeDetail : Routes("exchange_detail")
}