package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters.BudgetCategoryConverters
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters.LocalDateConverters
import java.time.LocalDate

@Entity(tableName = "budget_table")
@TypeConverters(
    BudgetCategoryConverters::class,
    LocalDateConverters::class
)
data class BudgetEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "budget_id")
    val budgetID: String,

    val name: String,

    val category: BudgetCategory,

    val createdAt: LocalDate,

    val finishedAt: LocalDate?
)