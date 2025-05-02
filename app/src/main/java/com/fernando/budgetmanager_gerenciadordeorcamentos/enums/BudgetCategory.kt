package com.fernando.budgetmanager_gerenciadordeorcamentos.enums

import androidx.annotation.DrawableRes
import com.fernando.budgetmanager_gerenciadordeorcamentos.R

enum class BudgetCategory(val text: String, @DrawableRes val drawableResId: Int) {
    COMPLETED("Completado", R.drawable.background_budget_category_completed),
    NOT_COMPLETED("Não completado", R.drawable.background_budget_category_not_completed),
    EXPIRED("Expirado", R.drawable.background_budget_category_expired);

    companion object {
        fun fromText(text: String) : BudgetCategory? {
            return entries.filter { budgetCategory ->
                budgetCategory.text == text
            }.getOrNull(0)
        }
    }
}