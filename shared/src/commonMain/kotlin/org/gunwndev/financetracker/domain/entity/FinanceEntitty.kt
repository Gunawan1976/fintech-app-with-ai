package org.gunwndev.financetracker.domain.entity

data class TransactionEntitty (
    public val name: String,
    public val category: String,
    public val expiryDateMillis: Long,
    public val amount: Long,
    public val totalAmount: Long,
){
    fun getDaysRemaining(currentDateMillis: Long):Int{
        val diffMillis = expiryDateMillis - currentDateMillis
        return (diffMillis / (1000 * 60 * 60 * 24)).toInt()
    }
}
