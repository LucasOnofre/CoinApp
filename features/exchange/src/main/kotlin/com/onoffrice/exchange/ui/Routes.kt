package com.onoffrice.exchange.ui

sealed class Routes(val rout: String) {
    object ExchangeList : Routes("exchange_list")
    object ExchangeDetail : Routes("exchange_detail")
}