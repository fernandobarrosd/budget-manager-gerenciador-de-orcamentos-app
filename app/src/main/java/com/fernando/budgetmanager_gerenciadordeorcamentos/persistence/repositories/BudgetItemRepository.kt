package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem

interface BudgetItemRepository {
    suspend fun saveBudgetItems(budgetID: String, budgetItems: List<BudgetItem>)
}