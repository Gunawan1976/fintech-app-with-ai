package org.gunwndev.financetracker.domain.entity

data class TransactionEntitty (
    val id: Int = 0,
    val name: String,
    val category: String,
    val expiryDateMillis :Long,
    val price:Int,
){
    fun getDaysRemaining(currentDateMillis: Long):Int{
        val diffMillis = expiryDateMillis - currentDateMillis
        return (diffMillis / (1000 * 60 * 60 * 24)).toInt()
    }
}
