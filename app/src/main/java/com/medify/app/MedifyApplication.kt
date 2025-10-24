package com.medify.app

import android.app.Application
import com.medify.app.di.viewModelModule
import org.koin.core.context.startKoin

class MedifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule)
        }
    }
}