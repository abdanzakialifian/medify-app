package com.medify.app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medify.app.data.network.request.LoginRequest
import com.medify.app.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailValueChange -> _uiState.update { it.copy(email = event.email) }
            is LoginEvent.OnPasswordValueChange -> _uiState.update { it.copy(password = event.password) }
            is LoginEvent.OnPasswordVisibilityChange -> _uiState.update { it.copy(isPasswordVisible = event.isPasswordVisible) }
            is LoginEvent.OnLoginClick -> login(LoginRequest(email = event.email, password = event.password))
            is LoginEvent.OnDismissPopupErrorDialog -> _uiState.update { it.copy(loginError = null) }
        }
    }

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoginLoading = true) }
            loginUseCase(request).fold(
                onSuccess = { token ->
                    _uiState.update { it.copy(loginToken = token) }
                },
                onFailure = { throwable ->
                    _uiState.update { it.copy(loginError = throwable) }
                }
            )
            _uiState.update { it.copy(isLoginLoading = false) }
        }
    }
}