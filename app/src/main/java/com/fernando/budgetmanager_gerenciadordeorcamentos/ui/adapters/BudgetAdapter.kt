package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.LayoutBudgetBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.enums.BudgetCategory

class BudgetAdapter(
    private val context: Context,
    private val budgets: List<Budget>) : RecyclerView.Adapter<BudgetAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetAdapter.ViewHolder {
        val binding = LayoutBudgetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = budgets.size

    override fun onBindViewHolder(viewHolder: BudgetAdapter.ViewHolder, position: Int) {
        val budget = budgets[position]
        viewHolder.bind(budget)
    }

    inner class ViewHolder(private val binding: LayoutBudgetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(budget: Budget) {
            binding.apply {
                textBudgetName.text = budget.name
                textBudgetCategory.text = budget.category.text
                textBudgetCreatedAt.text = budget.createdAt.formatDate()

                setBudgetCategoryTextBackground(budget.category)

            }
        }

        private fun setBudgetCategoryTextBackground(budgetCategory: BudgetCategory) {
            binding.textBudgetCategory.background = getDrawable(context, budgetCategory.drawableResId)
        }
    }

}