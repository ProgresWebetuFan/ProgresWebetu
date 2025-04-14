package com.zako.webetu.auth.login.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.copyright_2025
import webetu.composeapp.generated.resources.enterprise_resource
import webetu.composeapp.generated.resources.eye_off
import webetu.composeapp.generated.resources.eye_open
import webetu.composeapp.generated.resources.light_login_effect
import webetu.composeapp.generated.resources.login
import webetu.composeapp.generated.resources.ministry_of_higher_education_and_scientific_research
import webetu.composeapp.generated.resources.password
import webetu.composeapp.generated.resources.password_visibility_icon
import webetu.composeapp.generated.resources.planning
import webetu.composeapp.generated.resources.progress_banner
import webetu.composeapp.generated.resources.progress_brand_banner
import webetu.composeapp.generated.resources.registration_number
import webetu.composeapp.generated.resources.student_portal

@Composable
fun LoginScreenRoot(
    navigateToMainScreen: () -> Unit,
) {
    val loginScreenViewModel = koinViewModel<LoginScreenViewModel>()
    val state by loginScreenViewModel.state.collectAsState()

    LoginScreen(
        state = state,
        onRegistrationNumberChange = { registrationNumber ->
            loginScreenViewModel.action(
                LoginScreenActions.RegistrationNumberChange(
                    registrationNumber
                )
            )
        },
        onPasswordChange = { password ->
            loginScreenViewModel.action(LoginScreenActions.PasswordChange(password))
        },
        onShowPasswordClick = {
            loginScreenViewModel.action(LoginScreenActions.ShowPasswordClicked)
        },
        onHidePasswordClick = {
            loginScreenViewModel.action(LoginScreenActions.HidePasswordClicked)
        },
        onLoginClick = {
            loginScreenViewModel.action(
                LoginScreenActions.LoginClicked(
                    navigateAction = navigateToMainScreen
                )
            )
        }
    )
}

@Composable
private fun LoginScreen(
    state: LoginScreenState,
    onRegistrationNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onShowPasswordClick: () -> Unit,
    onHidePasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val verticalState = rememberScrollState()
    val registrationNumberFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    NetworkSquaresEffect(
        squaresCount = 9,
        squaresColor = MaterialTheme.colorScheme.background.copy(alpha = 0.05f),
        linesWight = 3f,
        modifier = modifier.verticalScroll(verticalState)
    ) {
        AnimatedVisibility(
            visible = state.isLoading
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
        HeadLine()
        Column(
            modifier = Modifier
                .padding(top = 33.dp)
                .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                modifier = Modifier
                    .width(265.dp)
                    .height(100.dp),
                painter = painterResource(Res.drawable.progress_banner),
                contentDescription = stringResource(Res.string.progress_brand_banner),
            )
            Spacer(modifier = Modifier.height(68.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                text = stringResource(Res.string.registration_number)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth()
                    .focusRequester(registrationNumberFocusRequester)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown && (event.key == Key.Tab || event.key == Key.Enter)) {
                            passwordFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                ),
                shape = MaterialTheme.shapes.medium,
                isError = state.error.isNotBlank(),
                supportingText = {
                    if (state.error.isNotBlank()) {
                        Text(
                            color = MaterialTheme.colorScheme.error,
                            text = state.error
                        )
                    }
                },
                value = state.registrationNumber,
                onValueChange = onRegistrationNumberChange,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                text = stringResource(Res.string.password)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Enter -> {
                                    if (state.registrationNumber.isNotBlank() && state.password.isNotBlank()) {
                                        onLoginClick()
                                    }
                                    true
                                }
                                Key.Tab -> {
                                    passwordFocusRequester.freeFocus()
                                    true
                                }
                                else -> false
                            }
                        } else {
                            false
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (state.registrationNumber.isNotBlank() && state.password.isNotBlank()) {
                            onLoginClick()
                        }
                    }
                ),
                isError = state.error.isNotBlank(),
                supportingText = {
                    if (state.error.isNotBlank()) {
                        Text(
                            color = MaterialTheme.colorScheme.error,
                            text = state.error
                        )
                    }
                },
                shape = MaterialTheme.shapes.medium,
                value = state.password,
                onValueChange = onPasswordChange,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                suffix = {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                if (state.isPasswordVisible) {
                                    onHidePasswordClick()
                                } else {
                                    onShowPasswordClick()
                                }
                            }
                            .size(24.dp),
                        painter = painterResource(
                            if (state.isPasswordVisible) Res.drawable.eye_open else Res.drawable.eye_off
                        ),
                        contentDescription = stringResource(Res.string.password_visibility_icon),
                    )
                }

            )

            Spacer(
                modifier = Modifier.height(29.dp)
            )

            Button(
                enabled = state.registrationNumber.isNotBlank() and state.password.isNotBlank(),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(min = 48.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = onLoginClick
            ) {
                Text(
                    text = stringResource(Res.string.login),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(
                modifier = Modifier.height(21.dp)
            )

            Text(
                text = stringResource(Res.string.ministry_of_higher_education_and_scientific_research),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(Res.string.copyright_2025),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun HeadLine() {
    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 48.dp),
            text = stringResource(Res.string.enterprise_resource),
            style = MaterialTheme.typography.headlineLarge,
            color = contentColorFor(MaterialTheme.colorScheme.primary),
            fontWeight = FontWeight.ExtraBold,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(Res.string.planning),
            style = MaterialTheme.typography.headlineLarge,
            color = contentColorFor(MaterialTheme.colorScheme.primary),
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(Res.string.student_portal),
            style = MaterialTheme.typography.bodyLarge,
            color = contentColorFor(MaterialTheme.colorScheme.primary),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun NetworkSquaresEffect(
    squaresCount: Int,
    squaresColor: Color,
    linesWight: Float,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .drawBehind {
                val canvasWidth = size.width
                val canvasHeight = size.height

                val squaresSize = canvasWidth / squaresCount

                val horizontalSquaresCount = canvasWidth / squaresSize
                val verticalSquaresCount = canvasHeight / horizontalSquaresCount

                // Draw horizontal lines
                for (i in 0..horizontalSquaresCount.toInt()) {
                    val x = i * squaresSize

                    drawLine(
                        color = squaresColor,
                        start = Offset(x, 0f),
                        end = Offset(x, canvasHeight),
                        strokeWidth = linesWight
                    )
                }

                // Draw vertical lines
                for (i in 0..verticalSquaresCount.toInt()) {
                    val y = i * squaresSize

                    drawLine(
                        color = squaresColor,
                        start = Offset(0f, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = linesWight
                    )
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(200.dp),
            painter = painterResource(Res.drawable.light_login_effect),
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}