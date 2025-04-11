package com.zako.webetu.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing

actual val Dispatchers.AppMain: CoroutineDispatcher
    get() = Dispatchers.Swing
