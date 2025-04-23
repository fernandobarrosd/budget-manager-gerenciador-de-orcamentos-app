package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem
import com.fernando.budgetmanager_gerenciadordeorcamentos.mappers.toRoomEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetItemRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetItemEntityDAO
import javax.inject.Inject

class LocalBudgetItemRepository @Inject constructor(private val budgetItemEntityDAO: BudgetItemEntityDAO)
    : BudgetItemRepository {
    override suspend fun saveBudgetItems(budgetID: String, budgetItems: List<BudgetItem>) {
        val budgetItemToSave = budgetItems.map { budgetItem ->
            budgetItem.toRoomEntity(budgetID)
        }

        budgetItemEntityDAO.saveBudgetItems(budgetItemToSave)
    }
}