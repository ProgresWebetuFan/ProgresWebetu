package com.zako.webetu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.zako.webetu.auth.user.UserController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var isUserLogin = false

        val job = CoroutineScope(Dispatchers.IO).launch {
            val user = UserController.getUserAuth()
            isUserLogin = user != null
        }

        installSplashScreen()
            .setKeepOnScreenCondition {
                !job.isCompleted
            }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            App(
                isUserLogin = isUserLogin,
            )
        }
    }
}
