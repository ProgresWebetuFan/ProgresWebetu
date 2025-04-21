package com.zako.webetu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.diamondedge.logging.KmLogging
import com.zako.webetu.auth.user.UserController
import com.zako.webetu.di.initKoin
import com.zako.webetu.splash.SplashScreen
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.ic_launcher_foreground
import java.lang.Thread.sleep



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

            var isUserLogin by remember { mutableStateOf(false) }
            var appState by remember { mutableStateOf(AppState.Splash) }


            rememberCoroutineScope().launch {
                    val user = UserController.getUserAuth()
                    isUserLogin = user != null
                    appState = AppState.App
            }

            if (appState == AppState.App) {
                App(
                    isUserLogin = isUserLogin,
                )
            } else {
                SplashScreen()
            }
        }
    }
}
