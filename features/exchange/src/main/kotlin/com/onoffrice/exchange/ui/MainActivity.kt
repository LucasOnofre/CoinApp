package com.onoffrice.exchange.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.onoffrice.core.resources.CoinAppTheme

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
                    CoinAppNavHost(navController)
                }
            }
        }
    }
}
