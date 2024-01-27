package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ExchangeDetail
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
class GetExchangeDetailTest {

    @RelaxedMockK
    private lateinit var repository: ExchangeRepository

    lateinit var interactor: GetExchangeDetail

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        interactor = GetExchangeDetail(repository)
    }

    @Test
    fun `get exchange list`() = runTest {
        val exchangeId = "ExchangeId"
        coEvery { repository.getExchangeDetail(exchangeId) } returns flow {
            emit(ResultWrapper.Success(exchangeDetailDomain))
        }

        interactor.executeUseCase(exchangeId).collect { result ->
            Assert.assertEquals(
                (result as ResultWrapper.Success).value.get(0).name, exchangeDetailDomain.get(0).name
            )
        }
    }

    @Test
    fun `get exchange list error`() = runTest {
        val exchangeId = "ExchangeId"
        coEvery { repository.getExchangeDetail(exchangeId) } returns flow {
            emit(errorMock)
        }

        interactor.executeUseCase(exchangeId).collect { result ->
            Assert.assertEquals(
                (result as ResultWrapper.Error).error?.error,
                errorMock.error?.error
            )
        }
    }
}

val exchangeDetailDomain = listOf(
    ExchangeDetail(
        name = "NAME",
        website = "WEBSITE"
    )
)
