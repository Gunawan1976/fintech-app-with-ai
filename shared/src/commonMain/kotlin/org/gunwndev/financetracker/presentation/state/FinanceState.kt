package org.gunwndev.financetracker.presentation.state

import org.gunwndev.financetracker.data.model.FinanceModel

data class FinanceState(
    val items: List<FinanceModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)