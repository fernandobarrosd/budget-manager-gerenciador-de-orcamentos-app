package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos

import androidx.room.Dao
import androidx.room.Insert
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetTagEntity

@Dao
interface BudgetTagEntityDAO {
    @Insert
    suspend fun saveBudgetTags(budgetTags: List<BudgetTagEntity>)
}