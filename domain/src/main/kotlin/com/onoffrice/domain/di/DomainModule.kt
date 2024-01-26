package com.onoffrice.domain.di

import com.onoffrice.domain.interactors.GetExchangeDetail
import com.onoffrice.domain.interactors.GetExchangeList
import org.koin.dsl.module

val domainModule = module {
    factory { GetExchangeList(repository = get()) }
    factory { GetExchangeDetail(repository = get()) }
}
