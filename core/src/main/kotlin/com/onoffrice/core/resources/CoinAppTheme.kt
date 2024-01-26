package com.onoffrice.core.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object CoinAppTheme {
    val colors: CoinAppColors
        @Composable @ReadOnlyComposable get() = LocalColors.current
    val dimensions: CoinAppDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current
}

@Composable
fun CoinAppTheme(
    colors: CoinAppColors = CoinAppTheme.colors,
    dimensions: CoinAppDimensions = CoinAppTheme.dimensions,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions
    ) {
        content()
    }
}
