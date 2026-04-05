package org.gunwndev.financetracker.presentation.event

import org.gunwndev.financetracker.data.model.FinanceModel


sealed interface FinanceEvent{
    object LoadItems : FinanceEvent
    data class DeleteItem(val item: FinanceModel) : FinanceEvent
    data class SaveItem(
        val name: String,
        val category: String,
        val expiryDateMillis: Long,
        val isConsumed: Boolean,
        val quantity: Int,
        val barcode: String?
    ) : FinanceEvent
}