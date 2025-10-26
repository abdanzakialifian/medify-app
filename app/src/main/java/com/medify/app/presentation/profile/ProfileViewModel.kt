package com.medify.app.presentation.profile

import androidx.lifecycle.ViewModel
import com.medify.app.R
import com.medify.app.resource.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {
    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnFirstNameValueChange -> _uiState.update { it.copy(firstName = event.firstName) }
            is ProfileEvent.OnLastNameValueChange -> _uiState.update { it.copy(lastName = event.lastName) }
            is ProfileEvent.OnIdNumberValueChane -> _uiState.update { it.copy(idNumber = event.idNumber) }
            is ProfileEvent.OnEmailValueChange -> _uiState.update { it.copy(email = event.email) }
            is ProfileEvent.OnPhoneNoValueChange -> _uiState.update { it.copy(phoneNo = event.phoneNo) }
        }
    }

    fun getAccountSettingMenus(): List<String> = listOf(
        resourceProvider.getString(R.string.profile_tab_profile),
        resourceProvider.getString(R.string.profile_tab_settings),
    )
}