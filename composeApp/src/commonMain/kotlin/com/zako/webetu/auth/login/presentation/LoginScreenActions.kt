package com.zako.webetu.auth.login.presentation

sealed interface LoginScreenActions {
    data class RegistrationNumberChange(val registrationNumber: String) : LoginScreenActions
    data class PasswordChange(val password: String) : LoginScreenActions
    data object ShowPasswordClicked : LoginScreenActions
    data object HidePasswordClicked : LoginScreenActions
    data class LoginClicked(
        val navigateAction: () -> Unit,
    ) : LoginScreenActions
}
