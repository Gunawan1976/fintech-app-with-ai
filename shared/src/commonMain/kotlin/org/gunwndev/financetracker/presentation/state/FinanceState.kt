package org.gunwndev.financetracker.presentation.state

import org.gunwndev.financetracker.data.model.FinanceModel
import org.gunwndev.financetracker.db.FinanceEntity
import org.gunwndev.financetracker.domain.entity.TransactionEntitty

data class FinanceState(
    val items: List<TransactionEntitty> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSheetOpen: Boolean = false // Tambahkan ini
) {
}