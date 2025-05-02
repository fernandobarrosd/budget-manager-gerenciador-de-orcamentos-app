package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.DialogFilterBudgetByCategoryNameBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.ui.viewmodels.BudgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBudgetByCategoryNameDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogFilterBudgetByCategoryNameBinding
    private val budgetsViewModel : BudgetsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DialogFilterBudgetByCategoryNameBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        budgetsViewModel.selectedRadioButtonId.observe(this) {selectedRadioButtonId ->
            if (selectedRadioButtonId == null) return@observe

            binding.categoriesRadioGroup.check(selectedRadioButtonId)
        }
    }

    private fun initListeners() {
        binding.btnApplyFilter.setOnClickListener {
            dismiss()
            val categoriesRadioGroup = binding.categoriesRadioGroup
            val selectedRadioButton = categoriesRadioGroup.getSelectedRadioButton()
            budgetsViewModel.updateSelectedRadioButtonId(categoriesRadioGroup.checkedRadioButtonId)
            budgetsViewModel.filterBudgetsByCategoryName(selectedRadioButton?.text.toString())
        }

        binding.btnRemoveFilter.setOnClickListener {
            dismiss()
            budgetsViewModel.removeCategoryFilter()
            budgetsViewModel.loadBudgets()
        }
    }


    private fun RadioGroup.getSelectedRadioButton(): RadioButton? {
        val selectedRadioButtonId = this.checkedRadioButtonId
        return this.findViewById(selectedRadioButtonId)
    }
}