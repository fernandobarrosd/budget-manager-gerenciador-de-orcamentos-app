package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetTagRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetTagEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetTagEntity
import javax.inject.Inject

class LocalBudgetTagRepository @Inject constructor(private val budgetTagEntityDAO: BudgetTagEntityDAO)
    : BudgetTagRepository {
    override suspend fun saveBudgetTags(budgetID: String, budgetTags: List<String>) {
        val budgetTagsToSave = budgetTags.map { budgetTag ->
            BudgetTagEntity(budgetTag, budgetID)
        }

        budgetTagEntityDAO.saveBudgetTags(budgetTagsToSave)
    }
}