package org.gunwndev.financetracker.presentation.event

import org.gunwndev.financetracker.db.FinanceEntity
import org.gunwndev.financetracker.domain.entity.TransactionEntitty


sealed interface FinanceEvent{
    object LoadItems : FinanceEvent
    data class DeleteItem(val item: TransactionEntitty) : FinanceEvent
    data class SaveItem(
        val name: String,
        val category: String,
        val expiryDateMillis :Long,
        val amount:Long,
        val total_amount:Long
    ) : FinanceEvent

    object DismissSheet : FinanceEvent
}