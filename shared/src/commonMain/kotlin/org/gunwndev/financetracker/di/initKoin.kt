package org.gunwndev.financetracker.di

import org.gunwndev.financetracker.di.module.dataModule
import org.gunwndev.financetracker.di.module.domainModule
import org.gunwndev.financetracker.di.module.presentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() {
    startKoin {
        modules(
            dataModule, domainModule, presentationModule
        )
    }
}