package org.gunwndev.financetracker.di

import org.gunwndev.financetracker.di.module.dataModule
import org.gunwndev.financetracker.di.module.domainModule
import org.gunwndev.financetracker.di.module.presentationModule
import org.gunwndev.financetracker.di.module.platformModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            dataModule, 
            domainModule, 
            presentationModule,
            platformModule
        )
    }
}