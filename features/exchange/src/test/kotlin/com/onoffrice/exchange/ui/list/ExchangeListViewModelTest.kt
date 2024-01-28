package com.onoffrice.exchange.ui.list

import app.cash.turbine.test
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeList
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.Exchange
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

private lateinit var viewModel: ExchangeListViewModel
private lateinit var testDispatchers: TestDispatchers

@MockK
private lateinit var interactor: GetExchangeList

@ExperimentalCoroutinesApi
class ExchangeListViewModelTest {

    @Before
    fun setup() {
        interactor = mockk()
        testDispatchers = TestDispatchers()
        viewModel = ExchangeListViewModel(interactor, testDispatchers)

    }

    @Test
    fun `when get exchange list is success and state flow is updated with state success and list`() =
        runBlocking {
            //When
            val expectedList = listOf(Exchange(name = "Test"))
            coEvery { interactor.execute() } returns flow { emit(ResultWrapper.Success(expectedList)) }

            //Do
            viewModel.getExchangeList()

            //Check
            //creates job to handle state updates
            val job = launch {
                viewModel.exchangeList.test {
                    val emission = awaitItem()
                    assert(emission.equals(UIState.Success(expectedList)))
                    cancelAndConsumeRemainingEvents()
                }
            }
            job.cancelAndJoin()
        }

    @Test
    fun `when get exchange list is error and state flow is updated with state error`() =
        runBlocking {
            //When
            val expectedError = ApiError("error")
            coEvery { interactor.execute() } returns flow { emit(ResultWrapper.Error(error = expectedError)) }

            //Do
            viewModel.getExchangeList()

            //Check
            //creates job to handle state updates
            val job = launch {
                viewModel.exchangeList.test {
                    val emission = awaitItem()
                    assert(emission.equals(UIState.Error(expectedError)))
                    cancelAndConsumeRemainingEvents()
                }
            }
            job.cancelAndJoin()
        }
}