package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetEntityWithItemsAndTags(
    @Embedded val budget: BudgetEntity,

    @Relation(
        parentColumn = "budget_id",
        entityColumn = "budget_item_id"
    )
    val items: List<BudgetItemEntity>,

    @Relation(
        parentColumn = "budget_id",
        entityColumn = "tag_name"
    )
    val tags: List<BudgetTagEntity>
)