package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetItemEntity

data class BudgetWithItems(
    @Embedded val budget: BudgetEntity,

    @Relation(
        parentColumn = "budget_id",
        entityColumn = "budget_id"
    )
    val items: List<BudgetItemEntity>
)