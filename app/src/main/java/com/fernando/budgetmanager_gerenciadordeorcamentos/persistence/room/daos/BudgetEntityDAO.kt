package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.relationships.BudgetWithItems

@Dao
interface BudgetEntityDAO {
    @Transaction
    @Query("SELECT * FROM budget_table ORDER BY name")
    suspend fun findAllBudgets() : List<BudgetWithItems>

    @Transaction
    @Query("SELECT * FROM budget_table WHERE name LIKE '%' || :budgetName || '%' ORDER BY name")
    suspend fun searchBudgetByName(budgetName: String) : List<BudgetWithItems>

    @Insert
    suspend fun saveBudget(budget: BudgetEntity)

    @Transaction
    @Query("SELECT * FROM budget_table WHERE budget_id = :budgetID")
    suspend fun findBudgetByID(budgetID: String) : BudgetWithItems

    @Transaction
    @Query("DELETE FROM budget_table WHERE budget_id = :budgetID")
    suspend fun deleteBudget(budgetID: String)

    @Transaction
    @Query("UPDATE budget_table SET category = 'COMPLETE' WHERE budget_id = :budgetID")
    suspend fun updateBudgetCategoryToComplete(budgetID: String) : Int

    @Transaction
    @Query("UPDATE budget_table SET category = 'EXPIRED' WHERE budget_id = :budgetID")
    suspend fun updateBudgetCategoryToExpired(budgetID: String) : Int

    @Transaction
    @Query("UPDATE budget_table SET name = :newName WHERE budget_id = :budgetID")
    suspend fun updateBudgetName(newName: String, budgetID: String) : Int
}