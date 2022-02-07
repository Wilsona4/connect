package com.example.connect.features.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usernameState = mutableStateOf("")
    val usernameState: State<String> get() = _usernameState

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String> get() = _passwordState

    private val _passwordVisibilityState = mutableStateOf(false)
    val passwordVisibilityState: State<Boolean> get() = _passwordVisibilityState


    fun setUsernameState(username: String) {
        _usernameState.value = username
    }

    fun setPasswordState(password: String) {
        _passwordState.value = password
    }

    fun togglePasswordVisibility(isVisible: Boolean){
        _passwordVisibilityState.value = isVisible
    }
}