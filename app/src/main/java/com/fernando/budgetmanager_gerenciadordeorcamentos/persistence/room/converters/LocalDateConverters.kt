package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverters {
    @TypeConverter
    fun localDateToString(localDate: LocalDate) : String {
        return localDate.toString()
    }

    @TypeConverter
    fun stringToLocalDate(localDateString: String) : LocalDate {
        return LocalDate.parse(localDateString)
    }
  }