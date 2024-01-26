package com.onoffrice.data.di

import com.onoffrice.data.repository.ExchangeRepositoryImpl
import com.onoffrice.domain.repository.ExchangeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ExchangeRepository> { ExchangeRepositoryImpl(api = get()) }
}
