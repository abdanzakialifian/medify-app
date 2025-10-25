package com.medify.app.di

import com.medify.app.resource.ResourceProvider
import com.medify.app.resource.ResourceProviderImpl
import org.koin.dsl.module

val resourceModule = module {
    single<ResourceProvider> { ResourceProviderImpl(get()) }
}