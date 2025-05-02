package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory

interface BudgetRepository {
    suspend fun saveBudget(budget: Budget)
    suspend fun findAllBudgets() : List<Budget>
    suspend fun findBudgetByID(budgetID: String) : Budget
    suspend fun findBudgetsByCategoryName(category: BudgetCategory) : List<Budget>
    suspend fun findBudgetsByItemsQuantity(quantity: Int) : List<Budget>
    suspend fun findBudgetsByCreatedAt(createdAt: Date) : List<Budget>
    suspend fun searchBudgetByName(budgetName: String) : List<Budget>
    suspend fun deleteBudgetByID(budgetID: String)
}