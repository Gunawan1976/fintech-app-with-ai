package org.gunwndev.financetracker.di.module

import org.gunwndev.financetracker.data.repository.FinanceRepositoryImpl
import org.gunwndev.financetracker.data.sources.local.FinanceDatabaseWrapper
import org.gunwndev.financetracker.domain.repository.FinanceRepository
import org.koin.dsl.module

val dataModule = module {

    single<FinanceRepository> { FinanceRepositoryImpl(get())
    }

    single { FinanceDatabaseWrapper(get()) }

}