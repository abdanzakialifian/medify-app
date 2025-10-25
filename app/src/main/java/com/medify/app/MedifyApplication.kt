package com.medify.app

import android.app.Application
import com.medify.app.di.networkModule
import com.medify.app.di.repositoryModule
import com.medify.app.di.resourceModule
import com.medify.app.di.useCaseModule
import com.medify.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MedifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MedifyApplication)
            modules(networkModule, repositoryModule, useCaseModule, resourceModule, viewModelModule)
        }
    }
}