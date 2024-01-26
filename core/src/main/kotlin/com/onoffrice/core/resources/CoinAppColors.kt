package com.onoffrice.core.resources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CoinAppColors(
    background: Color,
    black333333: Color,
    green006A4D: Color,
    white:Color
) {
    var background by mutableStateOf(background)
        private set
    var black333333 by mutableStateOf(black333333)
        private set
    var green006A4D by mutableStateOf(green006A4D)
        private set

    var white by mutableStateOf(white)
        private set

    fun copy(
        background: Color = this.background,
        black333333: Color = this.black333333,
        green006A4D: Color = this.green006A4D,
        white: Color = this.white,
    ): CoinAppColors = CoinAppColors(
        background = background,
        black333333 = black333333,
        green006A4D = green006A4D,
        white = white
    )

    fun updateColorsFrom(other: CoinAppColors) {
        background = other.background
        black333333 = other.black333333
        green006A4D = other.green006A4D
        white = other.white
    }
}

private val colorLightBackground = Color(0xFFF7F5F6)
private val colorLightBlack333333 = Color(0xFF000000)
val colorLightGreen006A4D = Color(0xFF2196F3)
val colorWhite = Color(0xFFFFFFFF)

fun colors(
    background: Color = colorLightBackground,
    black333333: Color = colorLightBlack333333,
    green006A4D: Color = colorLightGreen006A4D,
    white: Color = colorWhite,
): CoinAppColors = CoinAppColors(
    background = background,
    black333333 = black333333,
    green006A4D = green006A4D,
    white = white
)

val LocalColors = staticCompositionLocalOf { colors() }
