package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BudgetsViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository) : ViewModel() {
    private val _budgets: MutableLiveData<List<Budget>> = MutableLiveData()
    val budgets: LiveData<List<Budget>>
        get() = _budgets

    private var _searchedText: String = ""

    private var _selectedBudgetCategory : MutableLiveData<BudgetCategory?> = MutableLiveData(null)
    val selectedBudgetCategory : LiveData<BudgetCategory?>
        get() = _selectedBudgetCategory

    @DrawableRes
    private val _selectedRadioButtonId : MutableLiveData<Int?> = MutableLiveData()
    val selectedRadioButtonId : LiveData<Int?>
        get() = _selectedRadioButtonId

    fun loadBudgets() {
        viewModelScope.launch {
            if (_searchedText.isNotEmpty()) {
                searchBudgets()
            }
            val budgets = budgetRepository.findAllBudgets()
            _budgets.postValue(budgets)
        }
    }

    fun searchBudgets() {
        viewModelScope.launch {
            val budgetsSearched = budgetRepository.searchBudgetByName(_searchedText)
            _budgets.postValue(budgetsSearched)
        }

    }

    fun filterBudgetsByCategoryName(categoryName: String) {
        val category = BudgetCategory.fromText(categoryName) ?: return
        _selectedBudgetCategory.postValue(category)

        viewModelScope.launch {
            if (_searchedText.isNotEmpty()) {
                val budgets = budgetRepository.searchBudgetByName(_searchedText)
                val budgetsFilteredByCategoryName = budgets.filterByCategory(category)
                _budgets.postValue(budgetsFilteredByCategoryName.orderBudgetsByName())
                return@launch
            }
            val budgetsFilteredByCategoryName = budgetRepository.findBudgetsByCategoryName(category)
            _budgets.postValue(budgetsFilteredByCategoryName)
        }
    }

    fun updateSelectedRadioButtonId(checkedRadioButtonId: Int) {
        _selectedRadioButtonId.postValue(checkedRadioButtonId)
    }

    fun updateSearchedText(newSearchedText: String) {
        _searchedText = newSearchedText
    }

    fun removeCategoryFilter() {
        _selectedRadioButtonId.postValue(null)
        _selectedBudgetCategory.postValue(null)
    }

    private fun List<Budget>.orderBudgetsByName() : List<Budget> {
        return this.sortedWith(compareBy { it.name })
    }

    private fun List<Budget>.filterByCategory(category: BudgetCategory) : List<Budget>{
        return this.filter { budget ->
            budget.category == category
        }
    }
}