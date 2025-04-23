package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.mappers.toDomainEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.mappers.toRoomEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetEntityDAO
import javax.inject.Inject

class LocalBudgetRepository @Inject constructor(private val budgetEntityDAO: BudgetEntityDAO)
    : BudgetRepository {
    override suspend fun findAllBudgets(): List<Budget> {
        return budgetEntityDAO.findAllBudgets().map { budgetEntity ->
            budgetEntity.toDomainEntity()
        }
    }

    override suspend fun findBudgetByID(budgetID: String): Budget {
        val budget = budgetEntityDAO.findBudgetByID(budgetID)

        return budget.toDomainEntity()
    }

    override suspend fun deleteBudgetByID(budgetID: String) {
        budgetEntityDAO.deleteBudget(budgetID)
    }

    override suspend fun saveBudget(budget: Budget): Budget {
        val budgetToSave = budget.toRoomEntity()
        budgetEntityDAO.saveBudget(budgetToSave)
        return budget
    }
}