package com.onoffrice.exchange.ui.utils

import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail

val exchangeMock = Exchange(
    name = "Name",
    exchangeId = "Exchange ID",
    volume1dayUsd = 1.0
)

val exchangeDetailMock = ExchangeDetail(
    name = "Detail Name",
    exchangeId = "Detail ID",
    volume1dayUsd = 1.0
)