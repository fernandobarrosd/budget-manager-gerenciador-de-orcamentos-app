package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.FragmentHomeBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters.BudgetAdapter
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels.BudgetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val budgetsViewModel : BudgetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        binding.searchBudgetsByNameInput.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(budgetNameQuery: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(budgetNameQuery: String?): Boolean {
                if (budgetNameQuery == null) return false
                if (budgetNameQuery.isEmpty()) budgetsViewModel.loadBudgets()

                budgetsViewModel.searchBudgets(budgetNameQuery)

                return true
            }

        })
    }

    private fun setupRecyclerView() {
        binding.budgetsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        budgetsViewModel.budgets.observe(viewLifecycleOwner) { budgets ->
            val budgetAdapter = BudgetAdapter(requireContext(), budgets)
            binding.budgetsRecyclerView.adapter = budgetAdapter
        }
    }
}