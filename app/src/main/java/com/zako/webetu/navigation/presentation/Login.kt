package com.zako.webetu.navigation.presentation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zako.webetu.auth.login.presentation.LoginScreenRoot
import com.zako.webetu.navigation.model.Login


fun NavGraphBuilder.loginGraph(
    rootNavController: NavController
) {
    composable<Login>{
        LoginScreenRoot()
    }
}

