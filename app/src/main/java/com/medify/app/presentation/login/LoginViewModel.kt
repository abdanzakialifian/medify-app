package com.medify.app.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailValueChange -> _uiState.update { it.copy(email = event.email) }
            is LoginEvent.OnPasswordValueChange -> _uiState.update { it.copy(password = event.password) }
            is LoginEvent.OnPasswordVisibilityChange -> _uiState.update { it.copy(isPasswordVisible = !uiState.value.isPasswordVisible) }
        }
    }
}