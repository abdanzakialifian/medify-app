package com.medify.app.presentation.registration

import androidx.compose.runtime.Immutable

@Immutable
data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val idNumber: String = "",
    val email: String = "",
    val phoneNo: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
)
