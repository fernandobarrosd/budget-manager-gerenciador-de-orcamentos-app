package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.BudgetItems
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Name
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Price
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory
import java.time.LocalDateTime
import java.util.UUID

class Budget {
    private val _id: String
    val id: String
        get() = _id

    private val _name: Name
    val name: String
        get() = _name.value

    private var _category: BudgetCategory
    val category: BudgetCategory
        get() = _category

    private val _items: BudgetItems
    val items: MutableList<BudgetItem>
        get() = _items.value

    private val _createdAt: LocalDateTime
    val createdAt: LocalDateTime
        get() = _createdAt

    private val _finishedAt: LocalDateTime?
    val finishedAt: LocalDateTime?
        get() = _finishedAt

    private val _tags: MutableList<Name>
    val tags: List<String>
        get() = _tags.map { it.value }

    val total: Price
        get() = Price(items.sumOf { it.priceValue })


    constructor(id: String, name: Name, category: BudgetCategory,
                items: BudgetItems, createdAt: LocalDateTime,
                finishedAt: LocalDateTime?, tags: MutableList<Name>) {

        this._id = id
        this._name = name
        this._items = items
        this._createdAt = createdAt
        this._finishedAt = finishedAt
        this._category = category
        this._tags = tags
    }


    constructor(name: Name, items: BudgetItems, finishedAt: LocalDateTime?,
                tags: MutableList<Name>) {
        this._id = UUID.randomUUID().toString()
        this._createdAt = LocalDateTime.now()
        this._name = name
        this._category = BudgetCategory.NOT_COMPLETED
        this._items = items
        this._finishedAt = finishedAt
        this._tags = tags
    }

    fun complete() {
        if (this._category == BudgetCategory.COMPLETED) return

        if (allBudgetItemsIsCompleted()) {
            this._category = BudgetCategory.COMPLETED
        }
    }

    fun allBudgetItemsIsCompleted() : Boolean {
        return items.all { it.isCompleted }
    }

    fun expire() {
        if (this._finishedAt == null) return
        if (this._category == BudgetCategory.COMPLETED ||
            this._category == BudgetCategory.EXPIRED) return

        val currentDate = LocalDateTime.now()

        if (this._category == BudgetCategory.NOT_COMPLETED && currentDate.isAfter(finishedAt)) {
            this._category = BudgetCategory.EXPIRED
        }
    }
}