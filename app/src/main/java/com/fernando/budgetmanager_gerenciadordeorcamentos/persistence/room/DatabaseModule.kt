package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fernando.budgetmanager_gerenciadordeorcamentos.domain.factories.BudgetFactory
import com.fernando.budgetmanager_gerenciadordeorcamentos.mappers.toRoomEntity
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetEntityDAO
import com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.daos.BudgetItemEntityDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private lateinit var appDatabase: AppDatabase

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database")
            .addCallback(object: RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val budgets = List(10) {
                        BudgetFactory.createBudget(BudgetFactory.createBudgetItems())
                    }

                    budgets.forEach { budget ->
                        val budgetItemEntities = budget.items.map { budgetItem ->
                            budgetItem.toRoomEntity(budget.id)
                        }

                        CoroutineScope(Dispatchers.IO).launch {
                            appDatabase.budgetEntityDao().saveBudget(budget.toRoomEntity())
                            appDatabase.budgetItemEntityDao().saveBudgetItems(budgetItemEntities)
                        }
                    }
                }
            })
            .build()

        return appDatabase
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
}