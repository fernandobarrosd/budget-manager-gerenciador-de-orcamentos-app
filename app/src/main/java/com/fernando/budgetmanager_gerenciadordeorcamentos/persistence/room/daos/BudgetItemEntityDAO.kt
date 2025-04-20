package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos

import androidx.room.Dao
import androidx.room.Insert
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetItemEntity

@Dao
interface BudgetItemEntityDAO {
    @Insert
    suspend fun saveBudgetItems(budgetItems: List<BudgetItemEntity>)
}