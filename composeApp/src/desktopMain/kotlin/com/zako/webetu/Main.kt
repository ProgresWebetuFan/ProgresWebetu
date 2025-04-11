package com.zako.webetu

import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.zako.webetu.di.initKoin
import org.jetbrains.compose.resources.painterResource
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.ic_launcher_foreground

fun main() {
    application {
        initKoin()

        Window(
            onCloseRequest = ::exitApplication,
            title = "Webetu Desktop",
            icon = painterResource(Res.drawable.ic_launcher_foreground),
            onKeyEvent = {
                if (it.key == androidx.compose.ui.input.key.Key.Escape) {
                    exitApplication()
                    true
                } else {
                    false
                }
            }
        ) {
            App()
        }
    }
}
