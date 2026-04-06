package org.gunwndev.financetracker.presentation.state

import org.gunwndev.financetracker.data.model.FinanceModel
import org.gunwndev.financetracker.db.FinanceEntity

data class FinanceState(
    val items: List<FinanceEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)