package com.onoffrice.exchange.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onoffrice.core.resources.CoinAppTheme
import com.onoffrice.exchange.ui.detail.ExchangeDetailScreen
import com.onoffrice.exchange.ui.detail.ExchangeDetailViewModel
import com.onoffrice.exchange.ui.list.ExchangeListScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoinAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CoinAppTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.ExchangeList.rout
                    ) {
                        composable(Routes.ExchangeList.rout) {
                            ExchangeListScreen(navController = navController)
                        }

                        composable(
                            Routes.ExchangeDetail.rout + "{selected_exchange}",
                            arguments = listOf(
                                navArgument("selected_exchange") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            ExchangeDetailScreen(
                                backStackEntry = backStackEntry,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
