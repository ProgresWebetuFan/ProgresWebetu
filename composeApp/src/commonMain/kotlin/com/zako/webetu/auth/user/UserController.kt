package com.zako.webetu.auth.user

import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.auth.user.model.UserRepository
import com.zako.webetu.notification.snackbare.SnackbarController
import com.zako.webetu.utils.asString
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Controller for the user authentication
 * this object is aim to unify the access to the user in one place following the singleton pattern so that it's
 * impossible that can be many instances of the user repository in the app
 *
 * this class will be responsible for the response to the errors from the repo and handel it
 *
 * @author B.zakaria
 */
object UserController : KoinComponent {

    private val userRepository: UserRepository = get()

    suspend fun getUserAuth(): UserAuth? {
        return userRepository.getUserAuth()
            .onLeft { error ->
                SnackbarController.simpleSnackbar(error.errorMessage?.asString() ?: "Failed to get the user ")
            }
            .getOrNull()
    }

    suspend fun saveUserAuth(user: UserAuth): Boolean {
        val job = userRepository
            .saveUserAuth(user)
            .onLeft {
                SnackbarController.simpleSnackbar(it.errorMessage?.asString() ?: "Failed to save the user")
            }
            .getOrNull()
        return job == true
    }
}
