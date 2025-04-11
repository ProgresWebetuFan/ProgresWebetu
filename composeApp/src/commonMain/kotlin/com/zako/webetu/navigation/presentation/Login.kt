package com.zako.webetu.navigation.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zako.webetu.auth.login.presentation.LoginScreenRoot
import com.zako.webetu.navigation.model.AppGraph
import com.zako.webetu.navigation.model.Login

fun NavGraphBuilder.loginGraph(
    navHostController: NavHostController,
) {
    composable<Login> {
        LoginScreenRoot(
            navigateToMainScreen = {
                navHostController.navigate(AppGraph) {
                    popUpTo(Login) {
                        inclusive = true
                    }
                }
            }
        )
    }
}
