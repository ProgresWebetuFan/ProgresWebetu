package com.zako.webetu.navigation.presentation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zako.webetu.navigation.model.MainGraph

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
) {
    composable<MainGraph> { MainGraphRoot()  }
}