package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.FragmentBudgetsBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters.BudgetAdapter
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels.BudgetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetsFragment : Fragment() {
    private lateinit var binding: FragmentBudgetsBinding
    private val budgetsViewModel : BudgetsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBudgetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initObservers()
        initListeners()

        budgetsViewModel.loadBudgets()
    }

    private fun initListeners() {
        binding.searchBudgetsByNameSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(budgetNameQuery: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(budgetNameQuery: String?): Boolean {
                if (budgetNameQuery == null) return false
                budgetsViewModel.updateSearchedText(budgetNameQuery)

                if (budgetNameQuery.isEmpty()) budgetsViewModel.loadBudgets()

                budgetsViewModel.searchBudgets()

                return true
            }

        })
    }

    private fun setupRecyclerView() {
        binding.budgetsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        budgetsViewModel.budgets.observe(viewLifecycleOwner) { budgets ->
            binding.budgetsRecyclerView.adapter = BudgetAdapter(requireContext(), budgets)
        }
    }
}