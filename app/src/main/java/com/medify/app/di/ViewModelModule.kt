package com.medify.app.di

import com.medify.app.presentation.login.LoginViewModel
import com.medify.app.presentation.registration.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel() }
}