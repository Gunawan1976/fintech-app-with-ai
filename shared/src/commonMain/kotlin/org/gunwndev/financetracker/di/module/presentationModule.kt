package org.gunwndev.financetracker.di.module

import org.gunwndev.financetracker.presentation.viewmodel.FinanceViewModel
import org.koin.dsl.module

val presentationModule = module {

    factory {
        FinanceViewModel(
            get()
        )
    }

}