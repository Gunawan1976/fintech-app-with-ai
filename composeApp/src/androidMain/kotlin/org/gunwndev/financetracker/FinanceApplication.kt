package org.gunwndev.financetracker

import android.app.Application
import org.gunwndev.financetracker.di.initKoin

class FinanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
        )
    }
}