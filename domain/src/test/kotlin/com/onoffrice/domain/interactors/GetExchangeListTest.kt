package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.repository.ExchangeRepository
import com.onoffrice.domain.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetExchangeListTest {

    @RelaxedMockK
    private lateinit var repository: ExchangeRepository

    lateinit var interactor: GetExchangeList

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        interactor = GetExchangeListImpl(repository)
    }

    @Test
    fun `get exchange list`() = runTest {
        coEvery { repository.getExchangeList() } returns flow {
            emit(ResultWrapper.Success(exchangeDomain))
        }

        interactor.execute().collect { result ->
            Assert.assertEquals(
                (result as ResultWrapper.Success).value.get(0).name, exchangeDomain.get(0).name
            )
        }
    }

    @Test
    fun `get exchange list error`() = runTest {
        coEvery { repository.getExchangeList() } returns flow {
            emit(errorMock)
        }

        interactor.execute().collect { result ->
            Assert.assertEquals(
                (result as ResultWrapper.Error).error?.error,
                errorMock.error?.error
            )
        }
    }
}

val exchangeDomain = listOf(
    Exchange(
        name = "NAME",
        exchangeId = "Exchange ID",
        volume1dayUsd = 0.0
    )
)

val errorMock = ResultWrapper.Error(error = ApiError(error = "Error"))