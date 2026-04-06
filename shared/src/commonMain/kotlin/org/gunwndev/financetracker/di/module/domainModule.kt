package org.gunwndev.financetracker.di.module

import org.gunwndev.financetracker.domain.usecase.FinanceUsecase
import org.gunwndev.financetracker.presentation.viewmodel.FinanceViewModel
import org.koin.dsl.module

val domainModule = module {

    factory {
        FinanceUsecase.GetAllItem(get())
        FinanceUsecase.InsertItem(get())
        FinanceUsecase.DeleteItem(get())
    }

}