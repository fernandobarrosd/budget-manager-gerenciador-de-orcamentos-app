package com.fernando.budgetmanager_gerenciadordeorcamentos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fernando.budgetmanager_gerenciadordeorcamentos.R
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.LayoutBudgetBinding
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.entities.Budget
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.valueobjects.Date
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
                totalTextView.text = budget.total.convertPriceToBRLMoneyFormat()

                setBudgetCreatedAtTextView(budget)
                setBudgetCategoryTextBackground(budget.category)
                setMoneyCardViewBackground(budget.category)
            }
        }

        private fun setBudgetCreatedAtTextView(budget: Budget) {
            val budgetFinishedAt = budget.finishedAt

            binding.textBudgetCreatedAt.text = if (budgetFinishedAt != null) {
                "${budget.createdAt.formatDate()} até ${budgetFinishedAt.formatDate()}"
            } else {
                budget.createdAt.formatDate()
            }
        }

        private fun setMoneyCardViewBackground(budgetCategory: BudgetCategory) {
            @ColorRes
            val moneyCardBackgroundColor = when(budgetCategory) {
                BudgetCategory.COMPLETED -> R.color.green
                BudgetCategory.NOT_COMPLETED -> R.color.red
                BudgetCategory.EXPIRED -> R.color.yellow
            }

            binding.moneyIconCardView.setCardBackgroundColor(ContextCompat.getColor(context, moneyCardBackgroundColor))
        }

        private fun setBudgetCategoryTextBackground(budgetCategory: BudgetCategory) {
            binding.textBudgetCategory.background = getDrawable(context, budgetCategory.drawableResId)
        }
    }

}