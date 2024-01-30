package com.onoffrice.exchange.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.onoffrice.exchange.ui.detail.ExchangeDetailScreen
import com.onoffrice.exchange.ui.list.ExchangeListScreen

@Composable
    fun CoinAppNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Routes.ExchangeList.rout
        ) {
            composable(Routes.ExchangeList.rout) {
                ExchangeListScreen(navController = navController)
            }

            composable(
                Routes.ExchangeDetail.rout + "{$EXCHANGE_DETAIL_ROUTE_ARG}",
                arguments = listOf(
                    navArgument(EXCHANGE_DETAIL_ROUTE_ARG) { type = NavType.StringType },
                )
            ) {
                val param = it.arguments?.getString(EXCHANGE_DETAIL_ROUTE_ARG)
                ExchangeDetailScreen(
                    navController = navController,
                    selectedExchangeArg = param
                )
            }
        }
    }