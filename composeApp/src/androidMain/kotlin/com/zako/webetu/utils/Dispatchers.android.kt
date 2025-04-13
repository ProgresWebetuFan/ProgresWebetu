package com.zako.webetu.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val Dispatchers.AppMain: CoroutineDispatcher
    get() = Dispatchers.Main
