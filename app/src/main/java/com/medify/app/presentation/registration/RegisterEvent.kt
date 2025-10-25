package com.medify.app.presentation.registration

sealed interface RegisterEvent {
    data class OnFirstNameValueChange(val firstName: String) : RegisterEvent
    data class OnLastNameValueChange(val lastName: String) : RegisterEvent
    data class OnIdNumberValueChane(val idNumber: String) : RegisterEvent
    data class OnEmailValueChange(val email: String) : RegisterEvent
    data class OnPhoneNoValueChange(val phoneNo: String) : RegisterEvent
    data class OnPasswordValueChange(val password: String) : RegisterEvent
    data class OnConfirmPasswordValueChange(val confirmPassword: String) : RegisterEvent
    data class OnPasswordVisibilityChange(val isPasswordVisible: Boolean) : RegisterEvent
}