package com.zako.webetu.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.zako.webetu.navigation.model.AppGraph
import com.zako.webetu.navigation.model.Login
import com.zako.webetu.navigation.model.MainGraph
import com.zako.webetu.navigation.presentation.destinations.loginGraph
import com.zako.webetu.navigation.presentation.destinations.mainGraph

@Composable
fun NavigationHost(
    isUserLogin : Boolean ,
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        startDestination = if (isUserLogin) AppGraph else Login ,
        navController = rootNavController
    ) {
        loginGraph(rootNavController)
        navigation<AppGraph>(
            startDestination = MainGraph,
        ){
            mainGraph(rootNavController)
        }

    }
}
