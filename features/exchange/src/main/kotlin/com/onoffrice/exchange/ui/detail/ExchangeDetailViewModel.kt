package com.onoffrice.exchange.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.core.extensions.wrapResponse
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeDetail
import com.onoffrice.domain.model.ExchangeDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class ExchangeDetailViewModel(
    val getExchangeDetail: GetExchangeDetail
) : ViewModel() {

    private val _exchangeDetail = MutableStateFlow<UIState<List<ExchangeDetail>>>(UIState.Loading)
    val exchangeDetail = _exchangeDetail.asStateFlow()

    fun getExchangeDetailInfo(exchangeId: String?) {
        exchangeId?.let {
            getExchangeDetail.executeUseCase(it).map {
                _exchangeDetail.wrapResponse(it, false)
            }.catch { _exchangeDetail.value = UIState.UndefinedError(it) }
                .launchIn(viewModelScope)
        }
    }
}