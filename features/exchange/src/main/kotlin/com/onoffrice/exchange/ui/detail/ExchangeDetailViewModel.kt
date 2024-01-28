package com.onoffrice.exchange.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.core.extensions.wrapResponse
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetExchangeDetail
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.ExchangeDetail
import com.onoffrice.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(
    val getExchangeDetail: GetExchangeDetail,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _exchangeDetail = MutableStateFlow<UIState<List<ExchangeDetail>>>(UIState.Loading)
    val exchangeDetail = _exchangeDetail.asStateFlow()

    fun getExchangeDetailInfo(exchangeId: String?) {
        exchangeId?.let {
            viewModelScope.launch(dispatcher.main) {
                getExchangeDetail.execute(exchangeId)
                    .catch {
                        _exchangeDetail.value =
                            UIState.Error(ApiError(it.message ?: "Generic Error"))
                    }
                    .flowOn(dispatcher.main)
                    .collect {
                        _exchangeDetail.wrapResponse(it)
                    }
            }
        }
    }
}