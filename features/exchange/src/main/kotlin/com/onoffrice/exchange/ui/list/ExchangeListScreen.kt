package com.onoffrice.exchange.ui.list

import android.app.Activity
import android.graphics.fonts.FontStyle
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.onoffrice.core.extensions.formatCurrency
import com.onoffrice.core.resources.CoinAppTheme
import com.onoffrice.core.utils.UIState
import com.onoffrice.core.widget.LoadingWidget
import com.onoffrice.domain.model.Exchange
import com.onoffrice.exchange.R
import com.onoffrice.exchange.ui.Routes
import com.onoffrice.exchange.utils.createDialog
import org.koin.androidx.compose.getViewModel

@Composable
fun ExchangeListScreen(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: ExchangeListViewModel = getViewModel()
    val state by viewModel.exchangeList.collectAsState(initial = UIState.Loading)

    CoinAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Crossfade(
                modifier = Modifier.padding(),
                targetState = state,
                label = ""
            ) { exchangeListState ->
                when (exchangeListState) {
                    is UIState.Loading ->
                        LoadingWidget(
                            loading = true, modifier = Modifier.fillMaxSize()
                        )

                    is UIState.Success -> {
                        val items = exchangeListState.data
                        Column(
                            modifier = Modifier
                                .padding(CoinAppTheme.dimensions.paddingLarge)
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                                    bottom = CoinAppTheme.dimensions.paddingLarge
                                )
                            ) {
                                if (items.isEmpty()) {
                                    item {
                                        Text(
                                            text = stringResource(R.string.exchange_list_no_result),
                                            color = CoinAppTheme.colors.black333333,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(all = CoinAppTheme.dimensions.paddingLarge)
                                        )
                                    }
                                } else {
                                    items(items.size) { index ->
                                        val exchange = items[index]
                                        ExchangeItem(exchange = exchange, onSelectExchange = {
                                            navController.navigate(Routes.ExchangeDetail.rout + exchange.exchangeId)
                                        })
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        LaunchedEffect(true) {
                            val error = (exchangeListState as? Error)?.message
                            if (error != null) {
                                context.createDialog(
                                    title = R.string.unknown_error,
                                    message = error,
                                    positiveButton = R.string.ok
                                ) { dialog ->
                                    dialog.dismiss()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange, onSelectExchange: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(CoinAppTheme.dimensions.paddingMedium)
            .shadow(CoinAppTheme.dimensions.elevationDefault, RectangleShape)
    ) {
        Column(
            modifier = Modifier
                .clickable { onSelectExchange.invoke() }
                .fillMaxSize()
                .background(CoinAppTheme.colors.background)
                .padding(CoinAppTheme.dimensions.paddingLarge)

        ) {
            Row {
                ExchangeItemInfo(
                    title = stringResource(id = R.string.exchange_list_item_name),
                    content = exchange.name
                )
            }
            Row {
                ExchangeItemInfo(
                    title = stringResource(id = R.string.exchange_list_item_id),
                    content = exchange.exchangeId
                )
            }
            Row {
                ExchangeItemInfo(
                    title = stringResource(id = R.string.exchange_list_item_volume_1_day),
                    content = exchange.volume1dayUsd.formatCurrency()
                )
            }
            Spacer(modifier = Modifier.size(CoinAppTheme.dimensions.paddingSmall))
        }
    }
}

@Composable
private fun ExchangeItemInfo(title: String, content: String) {
    Text(
        text = title,
        color = CoinAppTheme.colors.black333333,
        style = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = CoinAppTheme.dimensions.paddingSmall),
        fontSize = 14.sp
    )
    Text(
        text = content,
        color = CoinAppTheme.colors.black333333,
        fontSize = 14.sp
    )
}
