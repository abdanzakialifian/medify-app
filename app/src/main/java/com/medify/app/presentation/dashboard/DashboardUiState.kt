package com.medify.app.presentation.dashboard

import androidx.compose.runtime.Immutable

@Immutable
data class DashboardUiState(
    val search: String = "",
)
