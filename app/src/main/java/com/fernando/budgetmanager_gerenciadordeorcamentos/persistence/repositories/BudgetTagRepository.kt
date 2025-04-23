package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories

interface BudgetTagRepository {
    suspend fun saveBudgetTags(budgetID: String, budgetTags: List<String>)
}