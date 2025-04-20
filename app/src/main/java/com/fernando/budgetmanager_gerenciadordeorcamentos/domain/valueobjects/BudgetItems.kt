package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem
import com.fernando.budgetmanager_gerenciadordeorcamentos.exceptions.BudgetItemsListEmptyException

class BudgetItems(budgetItems: MutableList<BudgetItem>) {
    private val _value: MutableList<BudgetItem> = budgetItems
    val value: MutableList<BudgetItem>
        get() = _value


    init {
        validate(budgetItems)
    }

    private fun validate(budgetItems: MutableList<BudgetItem>) {
        if (budgetItems.isEmpty()) {
            throw BudgetItemsListEmptyException("budget items list should be not empty")
        }
    }
}