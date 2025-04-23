package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.FragmentHomeBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters.BudgetAdapter
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel : HomeViewModel by viewModels()

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

        homeViewModel.loadBudgets()
    }

    private fun setupRecyclerView() {
        binding.budgetsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        homeViewModel.budgets.observe(viewLifecycleOwner) { budgets ->
            val budgetAdapter = BudgetAdapter(requireContext(), budgets)
            binding.budgetsRecyclerView.adapter = budgetAdapter
        }
    }
}