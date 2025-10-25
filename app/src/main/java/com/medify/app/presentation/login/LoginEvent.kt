package com.medify.app.presentation.login

sealed interface LoginEvent {
    data class OnEmailValueChange(val email: String) : LoginEvent
    data class OnPasswordValueChange(val password: String) : LoginEvent
    data class OnPasswordVisibilityChange(val isPasswordVisible: Boolean) : LoginEvent
    data class OnLoginClick(val email: String, val password: String) : LoginEvent
    data object OnDismissPopupErrorDialog : LoginEvent
}