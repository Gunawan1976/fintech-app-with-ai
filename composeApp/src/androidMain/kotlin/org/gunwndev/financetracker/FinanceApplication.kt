package org.gunwndev.financetracker

import android.app.Application
import org.gunwndev.financetracker.di.initKoin
import org.koin.android.ext.koin.androidContext

class FinanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@FinanceApplication)
        }
    }
}