package com.zako.webetu.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zako.webetu.auth.login.model.RemoteDataSource
import com.zako.webetu.auth.user.UserController
import com.zako.webetu.notification.snackbare.SnackbarController
import com.zako.webetu.utils.AppMain
import com.zako.webetu.utils.asString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.login_successful

class LoginScreenViewModel(
    private val remote: RemoteDataSource
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun action(action: LoginScreenActions) {
        when (action) {
            is LoginScreenActions.RegistrationNumberChange -> {
                _state.value = _state.value.copy(registrationNumber = action.registrationNumber)
            }

            is LoginScreenActions.PasswordChange -> {
                _state.value = _state.value.copy(password = action.password)
            }

            is LoginScreenActions.ShowPasswordClicked -> {
                _state.value = _state.value.copy(isPasswordVisible = true)
            }

            is LoginScreenActions.HidePasswordClicked -> {
                _state.value = _state.value.copy(isPasswordVisible = false)
            }

            is LoginScreenActions.LoginClicked -> {
                login(
                    _state.value.registrationNumber,
                    _state.value.password,
                    action.navigateAction
                )
            }
        }
    }

    fun login(
        registrationNumber: String,
        password: String,
        navigationAction: () -> Unit,
    ) {
        if (registrationNumber.isBlank() or password.isBlank()) {
            println("please fill all the fields !")
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = remote.login(
                registrationNumber = registrationNumber,
                password = password
            )

            result
                .onRight { userAuth ->
                    val message = getString(Res.string.login_successful)
                    UserController.saveUserAuth(userAuth)
                    SnackbarController.simpleSnackbar(
                        message = message
                    )
                    withContext(Dispatchers.AppMain) {
                        navigationAction()
                    }
                }
                .onLeft { appError ->
                    val message = appError.errorMessage?.asString()
                    SnackbarController.simpleSnackbar(message = message ?: "Login failed !")
                }

            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}
