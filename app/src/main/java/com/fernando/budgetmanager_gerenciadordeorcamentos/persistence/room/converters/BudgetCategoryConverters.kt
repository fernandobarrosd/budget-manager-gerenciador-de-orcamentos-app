package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters

import androidx.room.TypeConverter
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory

class BudgetCategoryConverters {
    @TypeConverter
    fun budgetCategoryToString(budgetCategory: BudgetCategory) : String {
        return budgetCategory.name
    }

    @TypeConverter
    fun stringToBudgetCategory(budgetCategoryString: String) : BudgetCategory {
        return BudgetCategory.valueOf(budgetCategoryString)
    }
}