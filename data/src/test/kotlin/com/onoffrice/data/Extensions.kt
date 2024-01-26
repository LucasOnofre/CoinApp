package com.onoffrice.data

import com.onoffrice.domain.utils.ResultWrapper

fun <T> T.success() = ResultWrapper.Success(this)