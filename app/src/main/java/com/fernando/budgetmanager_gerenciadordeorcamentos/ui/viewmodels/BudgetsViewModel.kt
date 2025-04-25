package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
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

    fun loadBudgets() {
        viewModelScope.launch {
            val budgetList = budgetRepository.findAllBudgets()
            _budgets.postValue(budgetList)
        }
    }

    fun searchBudgets(budgetNameSearchQuery: String) {
        viewModelScope.launch {
            val budgets = budgetRepository.searchBudgetByName(budgetNameSearchQuery)
            _budgets.postValue(budgets)
        }

    }
}