package com.medify.app.presentation.dashboard

sealed interface DashboardEvent {
    data class OnSearchValueChange(val search: String) : DashboardEvent
}