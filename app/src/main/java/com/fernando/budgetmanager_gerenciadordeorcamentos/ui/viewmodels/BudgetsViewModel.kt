package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
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

    private val _selectedDate: MutableLiveData<Date?> = MutableLiveData(null)
    val selectedDate: LiveData<Date?>
        get() = _selectedDate

    private val _selectedBudgetCategory : MutableLiveData<BudgetCategory?> = MutableLiveData()
    val selectedBudgetCategory : LiveData<BudgetCategory?>
        get() = _selectedBudgetCategory

    private val _isEnableButtonApplyFilters : MutableLiveData<Boolean> = MutableLiveData(false)
    val isEnableButtonApplyFilters : LiveData<Boolean>
        get() = _isEnableButtonApplyFilters


    @DrawableRes
    private val _selectedBudgetCategoryRadioButtonId : MutableLiveData<Int?> = MutableLiveData()
    val selectedBudgetCategoryRadioButtonId : LiveData<Int?>
        get() = _selectedBudgetCategoryRadioButtonId

    fun loadBudgets() {
        if (_searchedText.isNotEmpty()) {
            searchBudgets()
            return
        }
        viewModelScope.launch {
            val budgets = budgetRepository.findAllBudgets()
            _budgets.postValue(budgets)
        }
    }

    fun searchBudgets() {
        viewModelScope.launch {
            val selectedBudgetCategory = _selectedBudgetCategory.value

            if (selectedBudgetCategory != null) {
                val budgetsSearched = budgetRepository.searchBudgetByName(_searchedText)
                val budgetsFiltered = budgetsSearched.filterByCategory(selectedBudgetCategory)
                _budgets.postValue(budgetsFiltered.orderBudgetsByName())
                return@launch
            }

            val budgetsSearched = budgetRepository.searchBudgetByName(_searchedText)
            _budgets.postValue(budgetsSearched.orderBudgetsByName())
        }

    }

    fun filterBudgetsByCategoryName(categoryName: String) {
        val category = BudgetCategory.fromText(categoryName) ?: return

        viewModelScope.launch {
            if (_searchedText.isNotEmpty()) {
                val budgets = budgetRepository.searchBudgetByName(_searchedText)
                val budgetsFilteredByCategoryName = budgets.filterByCategory(category)
                _selectedBudgetCategory.postValue(category)
                enableButtonApplyFilters()
                _budgets.postValue(budgetsFilteredByCategoryName.orderBudgetsByName())
                return@launch
            }
            val budgetsFilteredByCategoryName = budgetRepository.findBudgetsByCategoryName(category)
            _selectedBudgetCategory.postValue(category)
            enableButtonApplyFilters()
            _budgets.postValue(budgetsFilteredByCategoryName.orderBudgetsByName())
        }
    }

    fun filterBudgetsByCreatedAt(createdAt: Date?) {
        val selectedBudgetCategory = _selectedBudgetCategory.value

        _selectedDate.value = createdAt
        val selectedDate = _selectedDate.value ?: return

        viewModelScope.launch {
            if (_searchedText.isNotEmpty() && selectedBudgetCategory != null) {
                val budgets = budgetRepository.searchBudgetByName(_searchedText)
                val budgetsFilteredByCategoryName = budgets.filterByCreatedAtAndCategory(
                    selectedDate, selectedBudgetCategory)
                _budgets.postValue(budgetsFilteredByCategoryName.orderBudgetsByName())
                return@launch
            }

            if (_searchedText.isNotEmpty()) {
                val budgets = budgetRepository.searchBudgetByName(_searchedText)
                val budgetsFiltered = budgets.filterByCreatedAt(selectedDate)
                _budgets.postValue(budgetsFiltered.orderBudgetsByName())
                return@launch
            }

            if (selectedBudgetCategory != null) {
                val budgets = budgetRepository.findBudgetsByCategoryName(selectedBudgetCategory)
                val budgetsFiltered = budgets.filterByCreatedAt(selectedDate)
                _budgets.postValue(budgetsFiltered.orderBudgetsByName())
                return@launch
            }

            val budgets = budgetRepository.findBudgetsByCreatedAt(selectedDate)
            _budgets.postValue(budgets)
        }
    }

    fun updateSelectedBudgetCategoryRadioButtonId(checkedRadioButtonId: Int?) {
        _selectedBudgetCategoryRadioButtonId.postValue(checkedRadioButtonId)
    }

    fun updateSearchedText(newSearchedText: String) {
        _searchedText = newSearchedText
    }

    fun enableButtonApplyFilters() {
        _isEnableButtonApplyFilters.postValue(true)
    }

    private fun disableButtonApplyFilters() {
        _isEnableButtonApplyFilters.postValue(false)
    }

    fun resetCategoryFilter() {
        _selectedBudgetCategory.value = null
        updateSelectedBudgetCategoryRadioButtonId(null)
        disableButtonApplyFilters()
        loadBudgets()
    }

    fun resetSelectedDate() {
        _selectedDate.postValue(null)
        loadBudgets()
    }

    private fun List<Budget>.orderBudgetsByName() : List<Budget> {
        return this.sortedWith(compareBy { it.name })
    }

    private fun List<Budget>.filterByCategory(category: BudgetCategory) : List<Budget>{
        return this.filter { budget ->
            budget.category == category
        }
    }

    private fun List<Budget>.filterByCreatedAt(createdAt: Date) : List<Budget> {
        return this.filter { budget ->
            budget.createdAt.isEqual(createdAt)
        }
    }

    private fun List<Budget>.filterByCreatedAtAndCategory(date: Date, category: BudgetCategory) : List<Budget> {
        return this.filter { budget ->
            budget.createdAt.isEqual(date) && budget.category == category
        }
    }
}