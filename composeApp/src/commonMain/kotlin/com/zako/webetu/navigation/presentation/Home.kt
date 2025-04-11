package com.zako.webetu.navigation.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zako.webetu.home.presentation.HomeScreenRoot
import com.zako.webetu.navigation.model.HomeScreen

fun NavGraphBuilder.homeGraph() {
    composable<HomeScreen> {
        HomeScreenRoot()
    }
}
