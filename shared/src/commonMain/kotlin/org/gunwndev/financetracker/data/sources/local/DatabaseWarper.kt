package org.gunwndev.financetracker.data.sources.local

import org.gunwndev.financetracker.DatabaseDriverFactory
import org.gunwndev.financetracker.db.AppDatabase

class FinanceDatabaseWrapper(
    driverFactory: DatabaseDriverFactory
) {
    val db = AppDatabase(driverFactory.createDriver())
}