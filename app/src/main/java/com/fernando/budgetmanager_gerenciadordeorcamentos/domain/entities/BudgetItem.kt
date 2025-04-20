package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Name
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Price
import java.math.BigDecimal
import java.util.UUID

class BudgetItem {
    private val _id: String
    val id: String
        get() = _id

    private val _name: Name
    val name: String
        get() = _name.value

    private val _price: Price
    val price: Price
        get() = _price

    val priceValue: BigDecimal
        get() = price.value

    private var _isCompleted: Boolean
    val isCompleted: Boolean
        get() = _isCompleted

    constructor(name: Name, price: Price) {
        this._id = UUID.randomUUID().toString()
        this._name = name
        this._price = price
        this._isCompleted = false
    }

    constructor(id: String, name: Name, price: Price, isCompleted: Boolean) {
        this._id = id
        this._name = name
        this._price = price
        this._isCompleted = isCompleted
    }

    fun complete() {
        if (this._isCompleted) return
        this._isCompleted = true
    }
}