package com.zako.webetu.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed class BaseString {
    class DynamicString(val value: String) : BaseString()
    class ResString(val value: StringResource) : BaseString()
}

// we does not make theme the same name cause of conflict's in the signature

@Composable
fun BaseString.asStringCompose(): String {
    return when (this) {
        is BaseString.DynamicString -> {
            this.value
        }
        is BaseString.ResString -> stringResource(this.value)
    }
}

suspend fun BaseString.asString(): String {
    return when (this) {
        is BaseString.DynamicString -> {
            this.value
        }
        is BaseString.ResString -> getString(this.value)
    }
}
