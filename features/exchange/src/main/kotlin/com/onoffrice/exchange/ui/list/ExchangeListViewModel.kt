package com.onoffrice.exchange.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.core.extensions.wrapResponse
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeList
import com.onoffrice.domain.model.Exchange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class ExchangeListViewModel(
    private val getExchange: GetExchangeList
) : ViewModel() {
    init {
        getExchangeList()
    }

    private val _exchangeList = MutableStateFlow<UIState<List<Exchange>>>(UIState.Loading)
    val exchangeList = _exchangeList.asStateFlow()

    private fun getExchangeList() {
        getExchange.executeUseCase().map {
            _exchangeList.wrapResponse(it, false)

        }.catch { _exchangeList.value = UIState.UndefinedError(it) }.launchIn(viewModelScope)
    }

}