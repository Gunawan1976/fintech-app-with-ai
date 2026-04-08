package org.gunwndev.financetracker.data.repository

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.gunwndev.financetracker.data.sources.local.FinanceDatabaseWrapper
import org.gunwndev.financetracker.domain.entity.TransactionEntitty
import org.gunwndev.financetracker.domain.repository.FinanceRepository

class FinanceRepositoryImpl(
    dbWrapper: FinanceDatabaseWrapper
) : FinanceRepository {

    private val queries = dbWrapper.db.financeEntityQueries
    override fun getAllItem(): Flow<List<TransactionEntitty>> {
        return queries.selectAll().asFlow().map {
            it.executeAsList().map { entity ->
                TransactionEntitty(
                    id = entity.id,
                    category = entity.category,
                    expiryDateMillis = entity.expiryDateMillis,
                    amount = entity.amount,
                    totalAmount = entity.totalAmount,
                    name = entity.name
                )
            }
        }
    }

    override suspend fun getItemById(id: Int): TransactionEntitty? {
        return queries.selectById(id.toLong()).executeAsOneOrNull()?.let { entity ->
            TransactionEntitty(
                id = entity.id,
                category = entity.category,
                expiryDateMillis = entity.expiryDateMillis,
                amount = entity.amount,
                totalAmount = entity.totalAmount,
                name = entity.name
            )
        }
    }

    override suspend fun insertItem(item: TransactionEntitty) {
        queries.insertTransaction(
            category = item.category,
            expiryDateMillis = item.expiryDateMillis,
            amount = item.amount,
            totalAmount = item.totalAmount,
            name = item.name,
        )
    }

    override suspend fun deleteItem(item: TransactionEntitty) {
        item.id?.let {
            queries.deleteTransactionById(it)
        }
    }
}