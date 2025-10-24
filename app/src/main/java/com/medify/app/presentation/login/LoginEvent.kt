package com.medify.app.presentation.login

sealed interface LoginEvent {
    data class OnEmailValueChange(val email: String) : LoginEvent
    data class OnPasswordValueChange(val password: String) : LoginEvent
    data object OnPasswordVisibilityChange : LoginEvent
}