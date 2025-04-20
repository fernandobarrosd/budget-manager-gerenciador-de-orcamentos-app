package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetTagEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tag_name")
    val tagName: String,

    @ColumnInfo(name = "budget_id")
    val budgetID: String
)