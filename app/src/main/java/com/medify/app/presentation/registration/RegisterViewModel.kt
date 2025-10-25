package com.medify.app.presentation.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<RegisterUiState> = MutableStateFlow(RegisterUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnFirstNameValueChange -> _uiState.update { it.copy(firstName = event.firstName) }
            is RegisterEvent.OnLastNameValueChange -> _uiState.update { it.copy(lastName = event.lastName) }
            is RegisterEvent.OnIdNumberValueChane -> _uiState.update { it.copy(idNumber = event.idNumber) }
            is RegisterEvent.OnEmailValueChange -> _uiState.update { it.copy(email = event.email) }
            is RegisterEvent.OnPhoneNoValueChange -> _uiState.update { it.copy(phoneNo = event.phoneNo) }
            is RegisterEvent.OnPasswordValueChange -> _uiState.update { it.copy(password = event.password) }
            is RegisterEvent.OnConfirmPasswordValueChange -> _uiState.update { it.copy(confirmPassword = event.confirmPassword) }
            is RegisterEvent.OnPasswordVisibilityChange -> _uiState.update { it.copy(isPasswordVisible = event.isPasswordVisible) }
        }
    }
}