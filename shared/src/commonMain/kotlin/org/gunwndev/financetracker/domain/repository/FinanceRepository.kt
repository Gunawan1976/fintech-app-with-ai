package org.gunwndev.financetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import org.gunwndev.financetracker.data.model.FinanceModel
import org.gunwndev.financetracker.domain.entity.FinanceEntitty

interface FinanceRepository {
    fun getAllItem(): Flow<List<FinanceModel>>
    suspend fun getItemById(id: Int): FinanceModel?
    suspend fun insertItem(item: FinanceModel)
    suspend fun deleteItem(item: FinanceModel)
}