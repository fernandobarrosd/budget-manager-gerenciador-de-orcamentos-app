package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class Price {
    private val _value: BigDecimal
    val value: BigDecimal
        get() = _value

    constructor(price: BigDecimal) {
        this._value = price
    }


    fun convertPriceToBRLMoneyFormat() : String = NumberFormat
        .getCurrencyInstance(Locale("pt", "BR"))
        .format(_value)
        .replace("\\u00A0", " ")
}