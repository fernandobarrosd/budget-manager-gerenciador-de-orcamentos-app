package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters.BigDecimalConverters
import java.math.BigDecimal

@Entity(tableName = "budget_item_table")
@TypeConverters(BigDecimalConverters::class)
data class BudgetItemEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "budget_item_id")
    val budgetItemID: String,

    @ColumnInfo(name = "budget_id")
    val budgetID: String,

    val name: String,

    val price: BigDecimal,

    val isCompleted: Boolean,
)