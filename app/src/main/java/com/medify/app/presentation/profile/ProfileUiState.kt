package com.medify.app.presentation.profile

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val idNumber: String = "",
    val email: String = "",
    val phoneNo: String = "",
)
