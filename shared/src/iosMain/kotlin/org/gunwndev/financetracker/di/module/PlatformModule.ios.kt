package org.gunwndev.financetracker.di.module

import org.gunwndev.financetracker.DatabaseDriverFactory
import org.gunwndev.financetracker.IOSDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
}
