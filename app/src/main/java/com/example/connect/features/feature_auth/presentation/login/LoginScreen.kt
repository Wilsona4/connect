package com.example.connect.features.feature_auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.connect.R
import com.example.connect.core.ui.components.DefaultTextField
import com.example.connect.core.ui.theme.Dimen_16dp
import com.example.connect.core.ui.theme.Dimen_24dp

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val usernameState = viewModel.usernameState
    val passwordState = viewModel.passwordState
    val isPasswordVisible = viewModel.passwordVisibilityState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = Dimen_24dp,
                end = Dimen_24dp,
                bottom = Dimen_24dp
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(Dimen_16dp))

            DefaultTextField(
                text = usernameState.value,
                hint = stringResource(id = R.string.username_hint),
                onValueChange = {
                    viewModel.setUsernameState(it)
                }
            )

            Spacer(modifier = Modifier.height(Dimen_16dp))

            DefaultTextField(
                text = passwordState.value,
                hint = stringResource(id = R.string.password_hint),
                onValueChange = {
                    viewModel.setPasswordState(it)
                },
                keyboardType = KeyboardType.Password,
                isPasswordVisible = isPasswordVisible.value,
                onPasswordToggleClicked = {
                    viewModel.togglePasswordVisibility(it)
                }
            )
        }

        Text(
            text = buildAnnotatedString {
                append("Don't have an account yet?")
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append("Sign up!")
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimen_16dp)
        )

    }
}

