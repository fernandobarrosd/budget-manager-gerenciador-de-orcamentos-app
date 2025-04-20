package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects

import com.fernando.budgetmanager_gerenciadordeorcamentos.exceptions.EmptyNameException

class Name(name: String) {
    private val _value: String = name
    val value: String
        get() = _value

    init {
        validateName(name)
    }

    private fun validateName(name: String) {
        if (name.isEmpty()) {
            throw EmptyNameException("name value should be not empty")
        }
    }
}