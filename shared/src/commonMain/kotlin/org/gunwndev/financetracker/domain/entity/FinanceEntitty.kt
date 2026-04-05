package org.gunwndev.financetracker.domain.entity

data class FinanceEntitty (
    val id: Int = 0,
    val name: String,
    val category: String,
    val expiryDateMillis :Long,
    val price:Int,
)
