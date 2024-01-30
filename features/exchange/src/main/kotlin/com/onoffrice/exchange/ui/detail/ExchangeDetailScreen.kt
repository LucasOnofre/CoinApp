package com.onoffrice.exchange.ui.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.onoffrice.core.extensions.formatCurrency
import com.onoffrice.core.resources.CoinAppTheme
import com.onoffrice.core.utils.UIState
import com.onoffrice.core.widget.LoadingWidget
import com.onoffrice.exchange.R
import com.onoffrice.exchange.utils.createDialog
import org.koin.androidx.compose.getViewModel

@Composable
fun ExchangeDetailScreen(
    navController: NavHostController?,
    selectedExchangeArg: String?
) {
    val context = LocalContext.current
    val viewModel: ExchangeDetailViewModel = getViewModel()
    val state by viewModel.exchangeDetail.collectAsState(initial = UIState.Loading)

    LaunchedEffect(key1 = null) {
        viewModel.getExchangeDetailInfo(selectedExchangeArg)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.exchange_details_header_title))
                },
                contentColor = CoinAppTheme.colors.white,
                backgroundColor = CoinAppTheme.colors.blue,
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Crossfade(
                modifier = Modifier.padding(),
                targetState = state,
                label = ""
            ) { exchangeDetailState ->
                when (exchangeDetailState) {
                    is UIState.Loading ->
                        LoadingWidget(
                            loading = true, modifier = Modifier.fillMaxSize()
                        )

                    is UIState.Success -> {
                        if (exchangeDetailState.data.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .padding(CoinAppTheme.dimensions.paddingLarge)
                            ) {
                                val currentExchangeInfo = exchangeDetailState.data[0]

                                Column(
                                    modifier = Modifier
                                        .padding(
                                            vertical = CoinAppTheme.dimensions.paddingMedium,
                                            horizontal = CoinAppTheme.dimensions.paddingLarge
                                        )
                                ) {
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_name),
                                        currentExchangeInfo.name
                                    )
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_website),
                                        currentExchangeInfo.website
                                    )
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_id),
                                        currentExchangeInfo.exchangeId
                                    )
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_volume_1_hour),
                                        currentExchangeInfo.volume1hrsUsd.formatCurrency()
                                    )
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_volume_1_day),
                                        currentExchangeInfo.volume1dayUsd.formatCurrency()
                                    )
                                    DetailItemText(
                                        stringResource(R.string.exchange_detail_volume_1_month),
                                        currentExchangeInfo.volume1mthUsd.formatCurrency()
                                    )
                                }
                            }
                        }
                    }

                    else -> {
                        LaunchedEffect(true) {
                            val error = (exchangeDetailState as? Error)?.message
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
private fun DetailItemText(title: String, content: String) {
    if (content.isNotEmpty()) {
      Row {
          Text(
              modifier = Modifier.padding(horizontal = CoinAppTheme.dimensions.paddingMedium),
              text = title,
              color = CoinAppTheme.colors.black333333,
              style = TextStyle(fontWeight = FontWeight.Bold),
              fontSize = 16.sp
          )
          Text(
              text = content,
              color = CoinAppTheme.colors.black333333,
              fontSize = 16.sp
          )
          Spacer(modifier = Modifier.size(CoinAppTheme.dimensions.paddingSmall))
      }
    }
}