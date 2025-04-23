package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence

import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetItemRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.repositories.BudgetTagRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories.LocalBudgetItemRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories.LocalBudgetRepository
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.repositories.LocalBudgetTagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PersistenceModule {
    @Binds
    fun budgetRepository(localBudgetRepository: LocalBudgetRepository) : BudgetRepository

    @Binds
    fun budgetItemRepository(
        localBudgetItemRepository: LocalBudgetItemRepository) : BudgetItemRepository

    @Binds
    fun budgetTagRepository(
        localBudgetTagRepository: LocalBudgetTagRepository) : BudgetTagRepository
}