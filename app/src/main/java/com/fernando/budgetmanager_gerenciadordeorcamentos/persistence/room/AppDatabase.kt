package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetItemEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetItemEntity

@Database(
    entities = [BudgetEntity::class, BudgetItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetEntityDao() : BudgetEntityDAO
    abstract fun budgetItemEntityDao() : BudgetItemEntityDAO
}