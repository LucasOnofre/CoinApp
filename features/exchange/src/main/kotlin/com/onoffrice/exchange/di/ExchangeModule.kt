package com.onoffrice.exchange.di

import com.onoffrice.exchange.ui.detail.ExchangeDetailViewModel
import com.onoffrice.exchange.ui.list.ExchangeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeModule = module {
    viewModel {
        ExchangeListViewModel(
            getExchange = get(),
            dispatcher = get()
        )
    }
    viewModel {
        ExchangeDetailViewModel(getExchangeDetail = get(), dispatcher = get())
    }
}