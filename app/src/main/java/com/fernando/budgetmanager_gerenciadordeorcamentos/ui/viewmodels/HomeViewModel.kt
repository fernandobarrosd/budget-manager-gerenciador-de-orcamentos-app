package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.BudgetItems
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Name
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Price
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID


class HomeViewModel : ViewModel() {
    private val _budgets: MutableLiveData<List<Budget>> = MutableLiveData()
    val budgets: LiveData<List<Budget>>
        get() = _budgets

    fun loadBudgets() {
        viewModelScope.launch {
            val budgetList = findAllBudgets()
            _budgets.postValue(budgetList)
        }
    }

    suspend fun findAllBudgets() : List<Budget>{
        val budgetItems = BudgetItems(mutableListOf(
            BudgetItem(
                name = Name("Item 1"),
                price = Price(BigDecimal("10.00"))
            ),
            BudgetItem(
                name = Name("Item 2"),
                price = Price(BigDecimal("10.00"))
            )
        ))

        return withContext(Dispatchers.IO) {
            mutableListOf(
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa1111111111111111111"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.EXPIRED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.COMPLETED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da casa11111111111111111111111111111111"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.NOT_COMPLETED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.EXPIRED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.EXPIRED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.COMPLETED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.COMPLETED
                ),
                Budget(
                    id = UUID.randomUUID().toString(),
                    name = Name("Construção da Casa"),
                    items = budgetItems,
                    tags = mutableListOf(),
                    createdAt = Date(LocalDate.now()),
                    finishedAt = null,
                    category = BudgetCategory.NOT_COMPLETED
                ),
            )
        }
    }
}