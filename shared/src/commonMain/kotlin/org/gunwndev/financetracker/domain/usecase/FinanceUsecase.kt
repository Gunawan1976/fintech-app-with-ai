package org.gunwndev.financetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.gunwndev.financetracker.db.FinanceEntity
import org.gunwndev.financetracker.domain.repository.FinanceRepository

class FinanceUsecase{
    class GetAllItem(
        private val repository: FinanceRepository
    ){
        suspend operator fun invoke(): Flow<List<FinanceEntity>> {
            return repository.getAllItem()
        }
    }

    class InsertItem(
        private val repository: FinanceRepository){
        suspend operator fun invoke(item: FinanceEntity){
            repository.insertItem(item)
        }
    }

    class DeleteItem(
        private val repository: FinanceRepository){
        suspend operator fun invoke(item: FinanceEntity){
            repository.deleteItem(item)
        }
    }

    class GetItemById(
        private val repository: FinanceRepository) {
        suspend operator fun invoke(id: Int): FinanceEntity? {
            return repository.getItemById(id)
        }
    }

}