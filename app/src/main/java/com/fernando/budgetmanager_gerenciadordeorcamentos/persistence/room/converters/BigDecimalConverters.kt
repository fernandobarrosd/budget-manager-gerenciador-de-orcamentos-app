package com.fernando.budgetmanager_gerenciadordeorcamentos.persistence.room.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverters {
    @TypeConverter
    fun bigDecimalToString(bigDecimal: BigDecimal) : String {
        return bigDecimal.toString()
    }

    @TypeConverter
    fun stringToBigDecimal(bigDecimalString: String) : BigDecimal {
        return BigDecimal(bigDecimalString)
    }
}