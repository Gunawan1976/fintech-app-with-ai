package org.gunwndev.financetracker.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.gunwndev.financetracker.data.sources.local.FinanceDatabaseWrapper
import org.gunwndev.financetracker.db.FinanceEntity
import org.gunwndev.financetracker.domain.repository.FinanceRepository

class FinanceRepositoryImpl(
    dbWrapper: FinanceDatabaseWrapper
) : FinanceRepository {

    private val queries = dbWrapper.db.financeEntityQueries
    override fun getAllItem(): Flow<List<FinanceEntity>> {
        return queries.selectAll().asFlow().map {
            it.executeAsList().map { entity ->
                FinanceEntity(
                    id = entity.id,
                    category = entity.category,
                    expiryDateMillis = entity.expiryDateMillis,
                    price = entity.price,
                    name = entity.name
                )
            }
        }
    }

    override suspend fun getItemById(id: Int): FinanceEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: FinanceEntity) {
        queries.insertTransaction(
            id = item.id,
            category = item.category,
            expiryDateMillis = item.expiryDateMillis,
            price = item.price,
            name = item.name
        )
    }

    override suspend fun deleteItem(item: FinanceEntity) {
        TODO("Not yet implemented")
    }

//    override suspend fun getTransactions(): List<Transacter.Transaction> {
//        return queries.selectAll()
//            .executeAsList()
//            .map {
//                Transacter.Transaction(
//                    id = it.id,
//                    amount = it.amount
//                )
//            }
//    }
//
//    override suspend fun addTransaction(transaction: Transacter.Transaction) {
//        queries.insertTransaction(
//            id = transaction.id,
//            amount = transaction.amount
//        )
//    }
}