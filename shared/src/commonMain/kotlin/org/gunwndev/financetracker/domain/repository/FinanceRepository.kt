package org.gunwndev.financetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import org.gunwndev.financetracker.data.model.FinanceModel
import org.gunwndev.financetracker.db.FinanceEntity
import org.gunwndev.financetracker.domain.entity.TransactionEntitty

interface FinanceRepository {
    fun getAllItem(): Flow<List<TransactionEntitty>>
    suspend fun getItemById(id: Int): TransactionEntitty?
    suspend fun insertItem(item: TransactionEntitty)
    suspend fun deleteItem(item: TransactionEntitty)
}