package com.medify.app.di

import com.medify.app.data.repository.MedifyRepositoryImpl
import com.medify.app.domain.repository.MedifyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MedifyRepository> { MedifyRepositoryImpl(get()) }
}