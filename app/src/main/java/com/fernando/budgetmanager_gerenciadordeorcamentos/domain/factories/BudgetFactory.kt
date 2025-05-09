package com.fernando.budgetmanager_gerenciadordeorcamentos.domain.factories

import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.BudgetItem
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.BudgetItems
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Name
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Price
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory
import io.github.serpro69.kfaker.Faker
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID

object BudgetFactory {

    fun createBudgetItems() : BudgetItems {
        return BudgetItems(mutableListOf(
            BudgetItem(
                name = Name("Item 1"),
                price = Price(BigDecimal("10.00"))
            ),
            BudgetItem(
                name = Name("Item 2"),
                price = Price(BigDecimal("20.00"))
            ),
            BudgetItem(
                name = Name("Item 3"),
                price = Price(BigDecimal("30.00"))
            ),
            BudgetItem(
                name = Name("Item 4"),
                price = Price(BigDecimal("40.00"))
            )
        ))
    }

    fun createBudget(budgetItems: BudgetItems) : Budget {
        val faker = Faker()
        val budgetCategory = enumValues<BudgetCategory>().random()
        val budgetName = faker.appliance.equipment()

        return Budget(
            id = UUID.randomUUID().toString(),
            name = Name(budgetName),
            items = budgetItems,
            createdAt = Date(LocalDate.now()),
            finishedAt = null,
            category = budgetCategory
        )
    }

    fun createBudgetWithFinishedAt(budgetItems: BudgetItems) : Budget {
        val faker = Faker()
        val budgetCategory = enumValues<BudgetCategory>().random()
        val budgetName = faker.appliance.equipment()

        return Budget(
            id = UUID.randomUUID().toString(),
            name = Name(budgetName),
            items = budgetItems,
            createdAt = Date(LocalDate.now()),
            finishedAt = Date(LocalDate.of(2025, Month.MAY, 12)),
            category = budgetCategory
        )
    }
}