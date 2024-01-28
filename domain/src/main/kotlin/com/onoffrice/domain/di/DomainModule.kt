package com.onoffrice.domain.di

import com.onoffrice.domain.interactors.GetExchangeDetail
import com.onoffrice.domain.interactors.GetExchangeDetailImpl
import com.onoffrice.domain.interactors.GetExchangeList
import com.onoffrice.domain.interactors.GetExchangeListImpl
import com.onoffrice.domain.utils.DefaultDispatchers
import com.onoffrice.domain.utils.DispatcherProvider
import org.koin.dsl.module

val domainModule = module {
    factory<GetExchangeList> { GetExchangeListImpl(repository = get()) }
    factory<GetExchangeDetail> { GetExchangeDetailImpl(repository = get()) }
    factory<DispatcherProvider> { DefaultDispatchers() }

}
