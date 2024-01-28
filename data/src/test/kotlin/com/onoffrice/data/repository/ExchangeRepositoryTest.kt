package com.onoffrice.data.repository

import com.onoffrice.data.api.ExchangeApi
import com.onoffrice.data.mapper.ExchangeDetailMapper
import com.onoffrice.data.mapper.ExchangeMapper
import com.onoffrice.data.model.ExchangeDetailResponse
import com.onoffrice.data.model.ExchangeResponse
import com.onoffrice.data.success
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.repository.ExchangeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeRepositoryTest {

    @RelaxedMockK
    private lateinit var api: ExchangeApi

    lateinit var repository: ExchangeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = ExchangeRepositoryImpl(api)
    }

    @Test
    fun `get exchange list`() = runBlocking {
        coEvery { api.getExchangeList() } returns exchangeResponse

        repository.getExchangeList().collect { result ->
            Assert.assertEquals(
                exchangeResponse.map { ExchangeMapper.mapToDomain(it) }.success(),
                result
            )
        }
    }

    @Test
    fun `get exchange detail`() = runBlocking {
        val exchangeID = "exchangeId"
        coEvery { api.getExchangeDetail(exchangeID) } returns exchangeDetailResponse

        repository.getExchangeDetail(exchangeID).collect { result ->
            Assert.assertEquals(
                exchangeDetailResponse.map { ExchangeDetailMapper.mapToDomain(it) }.success(),
                result
            )
        }
    }

    @Test
    fun `create exchange domain from response`() = runTest {
        val result = ExchangeMapper.mapToDomain(exchangeResponse[0])

        Assert.assertEquals(
            result.name, exchangeDomain[0].name
        )
    }

    @Test
    fun `create exchange detail from response`() = runTest {
        val result = ExchangeDetailMapper.mapToDomain(exchangeDetailResponse[0])

        Assert.assertEquals(
            result.website, exchangeDetailDomain[0].website
        )
    }
}

val exchangeResponse = listOf(
   ExchangeResponse(
       name = "NAME",
       exchangeId = "Exchange ID",
       volume1dayUsd = 0.0
   )
)

val exchangeDetailResponse = listOf(
    ExchangeDetailResponse(
        name = "NAME",
        website = "WEBSITE"
    )
)

val exchangeDomain = listOf(
    Exchange(
        name = "NAME",
        exchangeId = "Exchange ID",
        volume1dayUsd = 0.0
    )
)

val exchangeDetailDomain = listOf(
    ExchangeDetail(
        name = "NAME",
        website = "WEBSITE"
    )
)