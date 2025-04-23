package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget

interface BudgetRepository {
    suspend fun saveBudget(budget: Budget) : Budget
    suspend fun findAllBudgets() : List<Budget>
    suspend fun findBudgetByID(budgetID: String) : Budget
    suspend fun deleteBudgetByID(budgetID: String)
}