package com.fernando.budgetmanager_gerenciadordeorcamentos.mappers

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.BudgetItems
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Name
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Price
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetEntityWithItemsAndTags
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetItemEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.entities.BudgetTagEntity

fun Budget.toRoomEntity() : BudgetEntity {
    return BudgetEntity(
        budgetID = this.id,
        name = this.name,
        category = this.category,
        createdAt = this.createdAtValue,
        finishedAt = this.finishedAt
    )
}

fun BudgetItem.toRoomEntity(budgetID: String) : BudgetItemEntity {
    return BudgetItemEntity(
        budgetItemID = this.id,
        budgetID = budgetID,
        name = this.name,
        price = this.priceValue,
        isCompleted = this.isCompleted
    )
}

fun BudgetItemEntity.toDomainEntity() : BudgetItem {
    return BudgetItem(
        id = this.budgetItemID,
        name = Name(this.name),
        price = Price(this.price),
        isCompleted = this.isCompleted
    )
}

fun BudgetTagEntity.toDomainEntity() : String = this.tagName

fun BudgetEntityWithItemsAndTags.toDomainEntity() : Budget {
    val budgetItemsList = this.items.map { item -> item.toDomainEntity() }
        .toMutableList()

    val budgetTagNamesList = this.tags.map { tagName -> Name(tagName.toDomainEntity()) }
        .toMutableList()

    return Budget(
        id = this.budget.budgetID,
        name = Name(this.budget.name),
        createdAt = Date(this.budget.createdAt),
        finishedAt = this.budget.finishedAt?.let { Date(it) },
        category = this.budget.category,
        items = BudgetItems(budgetItemsList),
        tags = budgetTagNamesList
    )
}