package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room

import android.content.Context
import androidx.room.Room
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetItemEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetTagEntityDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    @Singleton
    fun budgetEntityDAO(appDatabase: AppDatabase) : BudgetEntityDAO {
        return appDatabase.budgetEntityDao()
    }

    @Provides
    @Singleton
    fun budgetItemEntity(appDatabase: AppDatabase) : BudgetItemEntityDAO {
        return appDatabase.budgetItemEntityDao()
    }

    @Provides
    @Singleton
    fun budgetTagEntityDAO(appDatabase: AppDatabase) : BudgetTagEntityDAO {
        return appDatabase.budgetTagDao()
    }
}