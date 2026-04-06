package org.gunwndev.financetracker.presentation.event

import org.gunwndev.financetracker.db.FinanceEntity


sealed interface FinanceEvent{
    object LoadItems : FinanceEvent
    data class DeleteItem(val item: FinanceEntity) : FinanceEvent
    data class SaveItem(
        val id: Long,
        val name: String,
        val category: String,
        val expiryDateMillis :Long,
        val price:Long,
    ) : FinanceEvent
}