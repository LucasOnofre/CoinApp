package com.onoffrice.core.extensions

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Date
import java.util.Locale

const val DATE_PATTERN = "yyyy-MM-dd"


fun Double?.formatCurrency(currencyCode: String? = null): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    currencyCode?.let { formatter.currency = Currency.getInstance(it) }
    return formatter.format(this.orZero())
}

fun Double?.orZero(): Double = this ?: 0.0

fun String.formatDate(
    fromPattern: String = DATE_PATTERN,
    toPattern: String = DATE_PATTERN
): String {
    if (isEmpty()) return this
    val dateFormatted = SimpleDateFormat(fromPattern, Locale.getDefault()).parse(this) ?: Date()
    return SimpleDateFormat(toPattern, Locale.getDefault()).format(dateFormatted)
}
