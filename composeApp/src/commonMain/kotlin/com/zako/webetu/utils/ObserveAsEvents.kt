package com.zako.webetu.utils

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

@Composable
expect fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
)
