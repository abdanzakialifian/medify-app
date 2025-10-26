package com.medify.app.presentation.profile

sealed interface ProfileEvent {
    data class OnFirstNameValueChange(val firstName: String) : ProfileEvent
    data class OnLastNameValueChange(val lastName: String) : ProfileEvent
    data class OnIdNumberValueChane(val idNumber: String) : ProfileEvent
    data class OnEmailValueChange(val email: String) : ProfileEvent
    data class OnPhoneNoValueChange(val phoneNo: String) : ProfileEvent
}