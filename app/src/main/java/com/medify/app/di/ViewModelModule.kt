package com.medify.app.di

import com.medify.app.presentation.dashboard.DashboardViewModel
import com.medify.app.presentation.login.LoginViewModel
import com.medify.app.presentation.profile.ProfileViewModel
import com.medify.app.presentation.registration.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel() }
    viewModel { DashboardViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}