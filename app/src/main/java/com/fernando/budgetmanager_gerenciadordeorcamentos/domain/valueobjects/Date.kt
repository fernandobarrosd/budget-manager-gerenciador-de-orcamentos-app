package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Date(private val _value: LocalDate) {
    val value: LocalDate
        get() = _value

    fun formatDate() : String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return dateTimeFormatter.format(value)
    }

    fun isEqual(date: Date) : Boolean {
        return value.isEqual(date.value)
    }

    fun isAfter(date: Date) : Boolean {
        return value.isAfter(date.value)
    }

}