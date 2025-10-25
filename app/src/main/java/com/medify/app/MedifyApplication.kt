package com.medify.app

import android.app.Application
import com.medify.app.di.networkModule
import com.medify.app.di.repositoryModule
import com.medify.app.di.useCaseModule
import com.medify.app.di.viewModelModule
import org.koin.core.context.startKoin

class MedifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}