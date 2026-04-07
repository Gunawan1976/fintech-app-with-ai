package org.gunwndev.financetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gunwndev.financetracker.domain.entity.TransactionEntitty
import org.gunwndev.financetracker.domain.repository.FinanceRepository
import org.gunwndev.financetracker.presentation.event.FinanceEvent
import org.gunwndev.financetracker.presentation.state.FinanceState

class FinanceViewModel(private val repository: FinanceRepository): ViewModel() {
    private val _state = MutableStateFlow(FinanceState())

    val state: StateFlow<FinanceState> = _state.asStateFlow()

    init {
        onEvent(FinanceEvent.LoadItems)
    }

    fun onEvent(event: FinanceEvent) {
        when (event) {
            is FinanceEvent.LoadItems -> loadItems()
            is FinanceEvent.DeleteItem -> deleteItem(event.item)
            is FinanceEvent.SaveItem -> saveItem(event.name, event.category, event.expiryDateMillis, amount =  event.amount, total_amount =  event.total_amount)
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                repository.getAllItem().collect { value ->
                    _state.update { it.copy(items = value, isLoading = false, error = null) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Terjadi Kesalahan tidak dikenal") }
            }
        }
    }

    private fun deleteItem(item: TransactionEntitty) {
        viewModelScope.launch {
            repository.deleteItem(item = item)
        }
    }

    private fun saveItem(
        name: String,
        category: String,
        expiryDateMillis: Long,
        amount: Long,
        total_amount: Long
    ) {
        viewModelScope.launch {
            val newItem = TransactionEntitty(
                name = name,
                category = category,
                expiryDateMillis = expiryDateMillis,
                amount = amount,
                totalAmount = total_amount
            )
            repository.insertItem(newItem)
        }
    }

//    fun load() {
//        viewModelScope.launch {
//            _state.value = getTransactions()
//        }
//    }
}