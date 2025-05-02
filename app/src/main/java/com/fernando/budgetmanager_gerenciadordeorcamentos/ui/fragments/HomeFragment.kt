package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.budgetmanager_gerenciadordeorcamentos.R
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.FragmentHomeBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters.BudgetAdapter
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.dialogs.FilterBudgetByCategoryNameDialog
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels.BudgetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val budgetsViewModel : BudgetsViewModel by activityViewModels()

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
                budgetsViewModel.updateSearchedText(budgetNameQuery)

                if (budgetNameQuery.isEmpty()) budgetsViewModel.loadBudgets()

                budgetsViewModel.searchBudgets()

                return true
            }

        })
        binding.btnFilterBudgetByCategory.setOnClickListener {
            val dialog = FilterBudgetByCategoryNameDialog()
            dialog.show(parentFragmentManager, "FilterBudgetsByCategoryNameDialog")
        }
    }

    private fun setupRecyclerView() {
        binding.budgetsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        budgetsViewModel.budgets.observe(viewLifecycleOwner) { budgets ->
            val budgetAdapter = BudgetAdapter(requireContext(), budgets)
            binding.budgetsRecyclerView.adapter = budgetAdapter
        }

        budgetsViewModel.selectedBudgetCategory.observe(viewLifecycleOwner) { selectedBudgetCategory ->
            if (selectedBudgetCategory == null) {
                binding.btnFilterBudgetByCategory.setDrawableEnd(R.drawable.arrow_down_icon)
                binding.btnFilterBudgetByCategory.setBackground(R.drawable.backgroud_button_select)
                binding.btnFilterBudgetByCategory.text =
                    ContextCompat.getString(requireContext(), R.string.budget_category_button_select_text)
                return@observe
            }
            
            binding.btnFilterBudgetByCategory.setDrawableEnd(R.drawable.check_icon)
            binding.btnFilterBudgetByCategory.setBackground(selectedBudgetCategory.drawableResId)
            binding.btnFilterBudgetByCategory.text = selectedBudgetCategory.text
        }
    }

    private fun AppCompatButton.setBackground(@DrawableRes drawableResId: Int) {
        this.background = ContextCompat.getDrawable(requireContext(), drawableResId)
    }

    private fun AppCompatButton.setDrawableEnd(@DrawableRes drawableResId: Int) {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null, null, ContextCompat.getDrawable(requireContext(), drawableResId), null
        )
    }
}