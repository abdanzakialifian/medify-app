package com.medify.app.presentation.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,

    val isLoginLoading: Boolean = false,
    val loginToken: String = "",
    val loginError: Throwable? = null,
)
