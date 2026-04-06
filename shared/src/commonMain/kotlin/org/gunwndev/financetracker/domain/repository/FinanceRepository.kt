package org.gunwndev.financetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import org.gunwndev.financetracker.data.model.FinanceModel
import org.gunwndev.financetracker.db.FinanceEntity

interface FinanceRepository {
    fun getAllItem(): Flow<List<FinanceEntity>>
    suspend fun getItemById(id: Int): FinanceEntity?
    suspend fun insertItem(item: FinanceEntity)
    suspend fun deleteItem(item: FinanceEntity)
}