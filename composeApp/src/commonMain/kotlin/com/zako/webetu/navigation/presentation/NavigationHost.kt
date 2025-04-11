package com.zako.webetu.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.zako.webetu.navigation.model.AppGraph
import com.zako.webetu.navigation.model.HomeScreen
import com.zako.webetu.navigation.model.Login

@Composable
fun NavigationHost(
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        startDestination = Login,
        navController = rootNavController
    ) {
        loginGraph(rootNavController)
        navigation<AppGraph>(
            startDestination = HomeScreen,
        ) {
            homeGraph()
        }
    }
}
