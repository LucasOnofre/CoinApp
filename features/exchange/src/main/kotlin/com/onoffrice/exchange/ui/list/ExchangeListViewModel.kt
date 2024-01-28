package com.onoffrice.exchange.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.core.extensions.wrapResponse
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeList
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.Exchange
import com.onoffrice.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getExchange: GetExchangeList,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _exchangeList = MutableStateFlow<UIState<List<Exchange>>>(UIState.Loading)
    val exchangeList = _exchangeList.asStateFlow()

    fun getExchangeList() {
        viewModelScope.launch(dispatcher.main) {
            getExchange.execute()
                .catch {
                    _exchangeList.value = UIState.Error(ApiError(it.message ?: "Generic Error"))
                }
                .flowOn(dispatcher.main)
                .collect {
                    _exchangeList.wrapResponse(it)
                }
        }
    }
}