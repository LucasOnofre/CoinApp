package com.onoffrice.exchange.ui.detail

import app.cash.turbine.test
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeDetail
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.utils.ResultWrapper
import com.onoffrice.exchange.ui.TestDispatchers
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

private lateinit var viewModel: ExchangeDetailViewModel
private lateinit var testDispatchers: TestDispatchers

@MockK
private lateinit var interactor: GetExchangeDetail

private val exchangeParam = "param"


@ExperimentalCoroutinesApi
class ExchangeDetailViewModelTest {

    @Before
    fun setup() {
        interactor = mockk()
        testDispatchers = TestDispatchers()
        viewModel = ExchangeDetailViewModel(interactor, testDispatchers)

    }

    @Test
    fun `when get exchange detail  is success and state flow is updated with state success and list`() =
        runBlocking {
            //When
            val expectedList = listOf(ExchangeDetail(name = "Test"))
            coEvery { interactor.execute(exchangeParam) } returns flow { emit(ResultWrapper.Success(expectedList)) }

            //Do
            viewModel.getExchangeDetailInfo(exchangeParam)

            //Check
            //creates job to handle state updates
            val job = launch {
                viewModel.exchangeDetail.test {
                    val emission = awaitItem()
                    assert(emission.equals(UIState.Success(expectedList)))
                    cancelAndConsumeRemainingEvents()
                }
            }
            job.cancelAndJoin()
        }

    @Test
    fun `when get exchange detail is error and state flow is updated with state error`() =
        runBlocking {
            //When
            val expectedError = ApiError("error")
            coEvery { interactor.execute(exchangeParam) } returns flow { emit(ResultWrapper.Error(error = expectedError)) }

            //Do
            viewModel.getExchangeDetailInfo(exchangeParam)

            //Check
            //creates job to handle state updates
            val job = launch {
                viewModel.exchangeDetail.test {
                    val emission = awaitItem()
                    assert(emission.equals(UIState.Error(expectedError)))
                    cancelAndConsumeRemainingEvents()
                }
            }
            job.cancelAndJoin()
        }
}