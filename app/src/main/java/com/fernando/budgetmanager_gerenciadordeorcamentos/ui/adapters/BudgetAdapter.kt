package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.fernando.budgetmanager_gerenciadordeorcamentos.R
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
                textBudgetCategory.text = budget.category.value
                textBudgetCreatedAt.text = budget.createdAt.formatDate()

                setBudgetCategoryTextBackground(budget.category)

            }
        }

        private fun setBudgetCategoryTextBackground(budgetCategory: BudgetCategory) {
            @DrawableRes
            val drawableResID =  when(budgetCategory) {
                BudgetCategory.COMPLETED -> R.drawable.background_budget_category_completed
                BudgetCategory.NOT_COMPLETED -> R.drawable.background_budget_category_not_completed
                BudgetCategory.EXPIRED -> R.drawable.background_budget_category_expired
            }

            binding.textBudgetCategory.background = getDrawable(context, drawableResID)
        }
    }

}