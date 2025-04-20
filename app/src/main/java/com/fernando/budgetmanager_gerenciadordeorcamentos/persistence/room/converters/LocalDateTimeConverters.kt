package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverters {
    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime) : String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun stringToLocalDateTime(localDateTimeString: String) : LocalDateTime {
        return LocalDateTime.parse(localDateTimeString)
    }
  }